## MySQL Database Design

### Table: patients

- id: INT, Primary Key, Auto Increment
- appointments_id: INT, Foreign Key → appointments(id)
- email: VARCHAR(254), NOT NULL, UNIQUE
- password: VARCHAR(255), NOT NULL

### Table: doctors

- id: INT, Primary Key, Auto Increment
- appointments_id: INT, Foreign Key → appointments(id)
- availability: BOOLEAN, DEFAULT TRUE
- email: VARCHAR(254), NOT NULL, UNIQUE
- password: VARCHAR(255), NOT NULL

### Table: appointments

- id: INT, Primary Key, Auto Increment
- patient_id: INT, Foreign Key → patients(id)
- doctor_id: INT, Foreign Key → doctors(id)
- appointment_time: DATETIME, NOT NULL
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)

### Table: admin

- id: INT, Primary Key, Auto Increment
- clinic_locations: INT, Foreign Key → clinic_locations(id)
- doctor_id: INT, Foreign Key → doctors(id)
- appointments_id: INT, Foreign Key → appointments(id)
- email: VARCHAR(254), NOT NULL, UNIQUE
- password: VARCHAR(255), NOT NULL

### Table: clinic_locations

- id: INT, Primary Key, Auto Increment
- name: VARCHAR(120), NOT NULL
- address_line: VARCHAR(255), NOT NULL
- city: VARCHAR(100), NOT NULL
- state: VARCHAR(100), NOT NULL
- postal_code: VARCHAR(20), NOT NULL
- phone: VARCHAR(20), NULL
- is_active: BOOLEAN, NOT NULL, DEFAULT TRUE

### Table: payments

- id: INT UNSIGNED, Primary Key, Auto Increment
- appointment_id: INT UNSIGNED, NOT NULL, Foreign Key → appointments(id)
- amount: DECIMAL(10,2), NOT NULL
- currency: CHAR(3), NOT NULL (ISO 4217 currency code)
- payment_method: VARCHAR(30), NULL
- status: TINYINT UNSIGNED, NOT NULL, DEFAULT 0 (0 = Pending, 1 = Paid, 2 = Refunded, 3 = Failed)
- paid_at: DATETIME, NULL
- created_at: DATETIME, NOT NULL, DEFAULT CURRENT_TIMESTAMP

### Table: prescriptions

- id: INT UNSIGNED, Primary Key, Auto Increment
- appointment_id: INT UNSIGNED, NOT NULL, Foreign Key → appointments(id)
- medication_name: VARCHAR(150), NOT NULL
- dosage: VARCHAR(100), NOT NULL
- frequency: VARCHAR(100), NOT NULL
- duration_days: SMALLINT UNSIGNED, NULL
- instructions: TEXT, NULL
- issued_at: DATETIME, NOT NULL, DEFAULT CURRENT_TIMESTAMP


## MongoDB Collection Design

MongoDB complements the structured MySQL clinic database by storing flexible **doctor–patient messages**, including optional attachments, tags, and delivery/read metadata. The core patient, doctor, appointment, and login records remain in MySQL.

### Collection: messages

Each document represents **one chat message** associated with a conversation and, when relevant, an appointment. The example uses MongoDB Extended JSON so the ObjectId and date fields remain valid JSON.

```json
{
  "_id": { "$oid": "672a4c89d6e3b0429f1a0087" },
  "schemaVersion": 1,
  "conversationId": "patient-15-doctor-3",
  "appointmentId": 51,
  "senderUserId": 42,
  "recipientUserId": 7,
  "messageType": "text",
  "body": "I have uploaded the test results for our appointment.",
  "attachments": [
    {
      "fileId": "upload-8f27",
      "fileName": "lab-results.pdf",
      "mimeType": "application/pdf",
      "sizeBytes": 184320,
      "storageKey": "private/messages/upload-8f27",
      "uploadedAt": { "$date": "2026-11-04T15:00:00Z" }
    }
  ],
  "tags": ["test-results", "follow-up"],
  "metadata": {
    "source": "patient-portal",
    "priority": "normal",
    "requiresResponse": true
  },
  "sentAt": { "$date": "2026-11-04T15:01:00Z" },
  "readAt": null,
  "editedAt": null,
  "isDeleted": false
}
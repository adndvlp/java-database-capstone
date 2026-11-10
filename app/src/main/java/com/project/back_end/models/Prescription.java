package com.project.back_end.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    private String patientName;


    @NotNull
    private Long appointmentId;

    @NotNull
    @Size(min = 3, max = 100)
    private String medication;

    @NotNull
    private String dosage;

    @Size(max = 200)
    private String doctorNotes;

    protected Prescription() {}

    public Prescription(
            String patientName,
            Long appointmentId,
            String medication,
            String dosage,
            String doctorNotes
    ) {
       this.patientName = patientName;
       this.appointmentId = appointmentId;
       this.medication = medication;
       this.dosage = dosage;
       this.doctorNotes = doctorNotes;
    }

    public String getId() {
        return this.id;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getMedication() {
        return this.medication;
    }

    public String getDosage() {
        return this.dosage;
    }

    public String getDoctorNotes() {
        return this.doctorNotes;
    }

    public Long getAppointmentId() {
        return this.appointmentId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public void setMedication(String medication) {
        this.medication = medication;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}

This Spring Boot application handles two distinct request flows depending on which module the user accesses.
When a user navigates to the Admin Dashboard or Doctor Dashboard, the request is routed to a Thymeleaf MVC controller. That controller delegates business logic to the shared service layer, which in turn calls JPA repositories backed by MySQL. The relational database stores the core domain entities: Patient, Doctor, Appointment, and Admin.
When a user accesses Appointments, PatientDashboard, or PatientRecord, the request goes instead to a REST controller. It calls the same service layer, but here the service delegates to a MongoDB repository, which reads and writes prescription documents stored as Mongo document models.
In both paths, all business logic is centralized in the service layer, the only difference is which persistence backend it delegates to.

1. User accesses AdminDashboard or Appointment pages.
2. The action is routed to the appropriate Thymeleaf or REST controller.
3. The controller calls the service layer, in case of consulting Appointments, PatientDashboard or PatientRecord, go to step 6
4. The service layer calls sql repositories which accesses to the MySQL database
5. The database calls via JPA Entity the models which are Patient, Doctor, Appointment, Admin
6. Via API and REST Controllers it calls the service layer that leverages a MongoDB repository
7. The Mongo Repository accesses de Mongo DB, Mongo Model, and the documents stored, in this case the prescriptions
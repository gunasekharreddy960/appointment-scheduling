
#  Appointment Scheduling API  

This is a **Spring Boot REST API** for managing lawyer-client appointments.  
It provides features for:  

- Listing lawyer availabilities  
- Scheduling appointments  
- Viewing appointments (by lawyer & client)  
- Cancelling appointments  
- Rescheduling appointments  

The application uses an **H2 in-memory database** for persistence and exposes endpoints for testing and integration.  

---

## ⚙️ Tech Stack  
- Java 17+  
- Spring Boot 3.x  
- Spring Data JPA  
- H2 Database (in-memory)  
- REST API (JSON-based)  

---

##  Running the Application  
1. Clone the repository and open it in your IDE (IntelliJ / Eclipse / STS).  
2. Run the Spring Boot application:  
The application will start at:
http://localhost:8080

## Start the Spring Boot application.
Open browser and go to 👉 http://localhost:8080/h2-console
Enter connection details:
JDBC URL: jdbc:h2:mem:appointmentdb
User Name: sa
Password: (leave empty)
Click Connect ✅

## Access API docs / endpoints:
API Base URL: http://localhost:8080/api
H2 Console: http://localhost:8080/h2-console


##  API Endpoints Overview  

## 🚀 API Endpoints Overview  

## 🚀 API Endpoints Overview  

| Method | Endpoint                                                                 | Description                  |
|--------|-------------------------------------------------------------------------|------------------------------|
| GET    | `http://localhost:8080/api/lawyers/{lawyerId}/availabilities`           | Get lawyer availabilities    |
| POST   | `http://localhost:8080/api/appointments`                                | Schedule an appointment      |
| GET    | `http://localhost:8080/api/lawyers/{lawyerId}/appointments`             | View appointments by lawyer  |
| GET    | `http://localhost:8080/api/clients/{clientId}/appointments`             | View appointments by client  |
| POST   | `http://localhost:8080/api/appointments/{appointmentId}/cancel`         | Cancel an appointment        |
| POST   | `http://localhost:8080/api/appointments/reschedule`                     | Reschedule an appointment    |




> # Court Booking system.

This is court booking system design to book play courts across cities and various courts.

### How it works

The application uses Spring boot application.

### Database

It uses a H2 in memory database (for now), can be changed easily in the `application.properties` for any other database.

### Getting started

You need Java 8 installed.

    mvn clean install

    java -jar target/court-booking-backend-0.0.1-SNAPSHOT.jar

### Run test

    mvn test

### Services
    
   ```
    POST /api/user
    GET /api/courts
    GET /api/slots/{courtId}
    POST /api/book
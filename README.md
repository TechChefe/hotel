# Hotel Booking REST API

A production-style backend REST API for hotel room booking management, 
built with Java and Spring Boot.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot
- **ORM:** Spring Data JPA (Hibernate)
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Utilities:** Lombok, Spring Validation

## Architecture

Standard Spring Boot layered architecture:

src/main/java/com/ots/hotel/

├── controllers/    # REST endpoints (@RestController)

├── dto/            # Request/Response data transfer objects

├── entities/       # Database entities (@Entity)

├── enums/          # Enumeration types

├── exception/      # Exception handling

├── repository/     # Data access via JPA (@Repository)

└── service/        # Business logic (@Service)

## Features

- Room management (create, update, delete, list)
- Booking management (create and manage reservations)
- Input validation and structured error handling
- Proper HTTP status codes and REST conventions

## Getting Started

### Prerequisites

- Java 17+
- PostgreSQL
- Maven

### Setup

1. Clone the repository
```bash
git clone https://github.com/TechChefe/hotel.git
cd hotel
```

2. Create a PostgreSQL database
```sql
CREATE DATABASE hotel_db;
```

3. Configure your database connection in
`src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

4. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

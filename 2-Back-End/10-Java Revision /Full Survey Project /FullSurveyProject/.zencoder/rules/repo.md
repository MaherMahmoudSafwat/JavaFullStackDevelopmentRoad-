---
description: Repository Information Overview
alwaysApply: true
---

# Full Survey Project Information

## Summary
A Spring Boot application for managing surveys. The project provides functionality for creating, managing, and responding to surveys with user authentication and data persistence using PostgreSQL.

## Structure
- **src/main/java**: Contains Java source code organized in packages
- **src/main/resources**: Application configuration, static resources, and templates
- **src/test**: Test classes for the application
- **target**: Compiled classes and build artifacts
- **logs**: Application log files

## Language & Runtime
**Language**: Java
**Version**: Java 17
**Build System**: Maven
**Package Manager**: Maven

## Dependencies
**Main Dependencies**:
- Spring Boot 3.5.6
- Spring Data JPA
- Spring Web
- PostgreSQL Driver
- Project Lombok
- Spring Boot Validation 4.0.0-M3

**Development Dependencies**:
- Spring Boot Test
- JUnit Jupiter

## Build & Installation
```bash
# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Package the application
mvn package
```

## Database Configuration
**Database**: PostgreSQL
**Connection**: JDBC with HikariCP connection pool
**Schema Management**: Hibernate with `update` strategy
**Configuration**: 
- URL: jdbc:postgresql://localhost:5432/FullSurveyProject
- Connection Pool: HikariCP with customized settings

## Application Structure
**Main Package**: com.Survey.FullSurveyProject
**Key Components**:
- **Models**: Entity classes (Users, Surveys, Answers, UsersImages)
- **Controllers**: REST endpoints for Users and Surveys
- **Services**: Business logic implementation
- **Repositories**: Data access interfaces
- **DTOs**: Data transfer objects for API requests/responses
- **Exceptions**: Custom exception classes

## Testing
**Framework**: JUnit Jupiter with Spring Boot Test
**Test Location**: src/test/java
**Configuration**: Spring Boot Test annotations
**Run Command**:
```bash
mvn test
```

## Logging
**Configuration**: Configured in application.yaml
**Log File**: logs/application.log
**Log Levels**: 
- Root: INFO
- Hibernate SQL: DEBUG
- Application: DEBUG
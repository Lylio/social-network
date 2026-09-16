# Social-Network

A social networking application built with Java 21, Spring Boot 3.5.16, MySQL, Thymeleaf, Spring Security, JPA/Hibernate and STOMP/WebSocket.

## Features

- Registration and form-based login
- User profiles with avatar URL and bio
- Timeline posts with optional uploaded photos
- One-to-one real-time messaging using STOMP over WebSocket
- Persistent chat history in MySQL
- Notification inbox plus real-time message notifications
- Admin dashboard for enabling/disabling users and moderating posts
- BCrypt password hashing and role-based authorization

## Run locally

### 1. Start MySQL

```bash
docker compose up -d
```

This starts MySQL on port `3306` with database `social_network`, username `root`, password `password`.

### 2. Start the application

```bash
mvn spring-boot:run
```

Open http://localhost:8080

### 3. Accounts

Register a normal account through `/register`.

A development admin is seeded automatically:

- username: `admin`
- password: `ChangeMe123!`

**Change or remove this development password before deployment.**

## Database environment variables

You can override defaults:

```text
DB_URL=jdbc:mysql://localhost:3306/social_network
DB_USERNAME=root
DB_PASSWORD=your-secret
```

## Suggested learning milestones

1. Understand entities, repositories, services and MVC controllers.
2. Add likes and comments with proper entity relationships.
3. Generate notifications when users interact.
4. Add friend/follow relationships.
5. Add tests for security, service and controller layers.
6. Replace local file storage with S3/Azure Blob in production.
7. Replace the simple in-memory STOMP broker with RabbitMQ when scaling to multiple app instances.
8. Add pagination, validation, rate limiting and moderation/audit logs.

## Important production notes

This is a learning scaffold, not a production-ready social network. Before public deployment add stronger upload validation, image processing, database migrations (Flyway/Liquibase), secure secrets, HTTPS, CSP/security headers, rate limiting, audit logging, automated tests, backups, and a proper notification event workflow.

## Why Spring Boot 3.5.16?

Spring Boot 4.1.1 is the current stable line, but this learning scaffold intentionally uses Spring Boot 3.5.16 because it stays on the Spring Framework 6 / Spring Security 6 generation that the current Thymeleaf Spring Security integration officially targets. Spring Boot 3.5.16 was the final OSS 3.5.x release. Once Thymeleaf Spring 7 support is mature, this project is a good candidate for a Boot 4 upgrade exercise.

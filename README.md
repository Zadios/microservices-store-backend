# Distributed Store System

This project is a microservices-based backend system designed for an e-commerce platform. It focuses on service decomposition, independent database management, and synchronous communication between modules.

## System Architecture

The system consists of three independent microservices:

- **Customer Service (Port 8081):** Manages user profiles and customer data.
- **Product Service (Port 8082):** Handles product catalog and inventory stock management.
- **Order Service (Port 8080):** Acts as an orchestrator. It processes purchase requests by communicating with both Customer and Product services to validate data and update stock levels.



## Tech Stack

- **Java 21**
- **Spring Boot 4.x**
- **Spring Data JPA**
- **MySQL** (Database-per-service pattern)
- **RestTemplate** (Inter-service communication)
- **SpringDoc OpenAPI** (API Documentation)

## Setup and Installation

1. **Database Setup:** Create the following schemas in your MySQL instance:
    - `db_bs_customers`
    - `db_bs_products`
    - `db_bs_orders`

2. **Configuration:** Update the `application.properties` file in each service with your local database credentials.

3. **Execution:** Run each service in the following order:
    - Customer Service
    - Product Service
    - Order Service

## API Documentation

Each service provides its own Swagger UI for endpoint testing:

- Order Service: `http://localhost:8080/swagger-ui.html`
- Customer Service: `http://localhost:8081/swagger-ui.html`
- Product Service: `http://localhost:8082/swagger-ui.html`

## Current Development Status

- Service-to-service communication implemented.
- Automatic stock reduction upon order placement.
- Centralized exception handling.

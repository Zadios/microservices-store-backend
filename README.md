# Distributed Store System (Microservices & Security)

This project is a microservices-based backend system designed for an e-commerce platform. It focuses on service decomposition, centralized security, and an API Gateway architecture.

## System Architecture

The system uses an **API Gateway** pattern as a single entry point, ensuring that all requests are authenticated before reaching the business logic.

- **API Gateway (Port 8080):** Centralized entry point. Implements a custom **Reactive Global Filter** for JWT validation.
- **Auth Service (Port 8083):** Manages user authentication, JWT generation, and token validation.
- **Customer Service (Port 8081):** Manages user profiles and customer data.
- **Product Service (Port 8082):** Handles product catalog and inventory stock management.
- **Order Service (Port 8084):** Processes purchase requests and orchestrates stock updates.



## Key Features

- **Centralized Security:** All requests to business services must include a valid Bearer Token validated at the Gateway.
- **Service Independence:** Each module has its own lifecycle and database.
- **Non-blocking Gateway:** Built with Spring Cloud Gateway and WebFlux for high performance.

## Tech Stack

- **Java 21**
- **Spring Boot 3.4.x / 4.x**
- **Spring Cloud Gateway** (Reactive)
- **Spring Security & JWT**
- **Spring Data JPA**
- **MySQL** (Database-per-service pattern)
- **WebClient** (Asynchronous inter-service communication)

## Setup and Installation

1. **Database Setup:** Create the following schemas in MySQL:
    - `db_bs_customers`, `db_bs_products`, `db_bs_orders`, `db_bs_auth`

2. **Execution Order:**
    1. **Auth Service** (Required for token generation)
    2. **Business Services** (Customer, Product, Order)
    3. **API Gateway** (The entry point)

## How to Test

1. **Login:** Send a POST to `http://localhost:8080/auth/login` to get your JWT.
2. **Authorized Request:** Use the token in the `Authorization` header as `Bearer <token>` to access:
    - `http://localhost:8080/products/**`
    - `http://localhost:8080/customers/**`
    - `http://localhost:8080/orders/**`
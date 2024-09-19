# GestionStock

GestionStock is a comprehensive stock management application that allows businesses to efficiently manage their articles, stock, clients, suppliers, customer orders, supplier orders, user access roles, sales statistics, and trend analysis. The application uses a modern technology stack to provide a robust and scalable solution.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Security](#security)
- [Contributing](#contributing)

## Features

- **User Management**: Create and manage user roles and permissions.
- **Stock Management**: Track inventory levels, manage stock entries and exits.
- **Order Management**: Handle customer and supplier orders.
- **Client and Supplier Management**: Manage details of clients and suppliers.
- **Analytics**: Sales statistics and trend analysis.
- **API Documentation**: Interactive API documentation with OpenAPI.

## Technology Stack

- **Backend**: Spring Boot
- **Frontend**: Angular
- **Database**: MySQL (or other supported databases)
- **Authentication**: JWT (JSON Web Token)
- **Containerization**: Docker
- **API Documentation**: Springdoc OpenAPI

## Setup and Installation

### Prerequisites

- Java 17+
- Node.js & npm
- MySQL or other supported databases
- Docker (optional, for containerization)

### Backend Setup

1. Clone the repository:
   ```
   git clone https://github.com/username/gestionstock.git
   ```
2. Navigate to the backend directory:
   ```
   cd gestionstock/backend
   ```
3. Configure your database settings in `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/gestionstock
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```
4. Build and run the application:
   ```
   ./mvnw spring-boot:run
   ```

### Frontend Setup

1. Navigate to the frontend directory:
   ```
   cd gestionstock/frontend
   ```
2. Install dependencies:
   ```
   npm install
   ```
3. Start the development server:
   ```
   ng serve
   ```

## Configuration

Configuration settings, such as database connections and API keys, are managed via the `application.properties` file for the backend and `environment.ts` for the frontend. Ensure you secure sensitive information.

## Usage

1. Access the application via `http://localhost:4200` (or your configured frontend URL).
2. Use the provided credentials to log in and explore the features.
3. API documentation is available at `http://localhost:8080/swagger-ui.html` (or your configured backend URL).

## Security

The application uses Spring Security with JWT for authentication. Ensure you update the security settings in the `SecurityConfiguration` class and use secure practices in production.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Make sure to follow the project's code style and add appropriate tests.

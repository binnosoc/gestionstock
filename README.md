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

## Security

The application uses Spring Security with JWT for authentication. Ensure you update the security settings in the `SecurityConfiguration` class and use secure practices in production.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Make sure to follow the project's code style and add appropriate tests.

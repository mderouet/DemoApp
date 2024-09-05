# Demo Spring Boot Application

![Java CI with Gradle](https://github.com/mderouet/DemoApp/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![codecov](https://codecov.io/gh/mderouet/DemoApp/branch/main/graph/badge.svg)](https://codecov.io/gh/mderouet/DemoApp)

This is a demo Spring Boot application that implements CRUD operations for a Person entity using Java 21 and Spring Boot 3.x.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Clone the Repository](#clone-the-repository)
  - [Build the Project](#build-the-project)
  - [Run the Application](#run-the-application)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Testing](#testing)
- [CI/CD](#cicd)
- [Docker](#docker)
- [Contributing](#contributing)
- [License](#license)

## Features

- CRUD operations for Person entity
- RESTful API using Spring Web
- Spring Data JPA for database operations
- H2 in-memory database for development and testing
- Gradle build system
- Continuous Integration with GitHub Actions
- Java 21 support
- Docker containerization

## Prerequisites

- Java 21 or later
- Gradle 8.x or later
- Docker (optional, for containerization)



### Build the Project

To build the project, run:
```bash
./gradlew build
```

### Run the Application

To run the application, execute:
```bash
./gradlew bootRun

## Getting Started
The application will start on `http://localhost:8080`.

## Project Structure

The project follows a standard Spring Boot application structure:

src
├── main
│ ├── java
│ │ └── com
│ │ └── example
│ │ └── demoapp
│ │ ├── DemoAppApplication.java
│ │ ├── controller
│ │ ├── model
│ │ ├── repository
│ │ └── service
│ └── resources
│ └── application.properties
└── test
└── java
└── com
└── example
└── demoapp
└── DemoAppApplicationTests.java
### Clone the Repository
## API Endpoints

- `GET /api/persons`: Retrieve all persons
- `GET /api/persons/{id}`: Retrieve a specific person
- `POST /api/persons`: Create a new person
- `PUT /api/persons/{id}`: Update an existing person
- `DELETE /api/persons/{id}`: Delete a person

## Database

The application uses an H2 in-memory database for development and testing purposes. The database is automatically configured by Spring Boot.

## Testing

To run the tests, execute:

The project includes unit tests and integration tests to ensure the functionality of the application.

## CI/CD

This project uses GitHub Actions for Continuous Integration and Continuous Deployment. The workflow is defined in `.github/workflows/gradle.yml`.

The CI pipeline performs the following steps:
1. Checks out the code
2. Sets up JDK 21
3. Builds the project with Gradle
4. Runs tests

You can view the CI results in the Actions tab of the GitHub repository.

## Docker

To build and run the application using Docker:

1. Build the Docker image:
   ```bash
   docker build -t demoapp .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 demoapp
   ```

The application will be accessible at `http://localhost:8080`.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
# Directa24 Back-End Developer Challenge

In this challenge, the REST API contains information about a collection of movies released after the year 2010, directed by acclaimed directors.
Given the threshold value, the goal is to use the API to get the list of the names of the directors with most movies directed. Specifically, the list of names of directors with movie count strictly greater than the given threshold.
The list of names must be returned in alphabetical order.

## Project Setup

### Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- An IDE (e.g., IntelliJ IDEA) or text editor
- Internet connection (to fetch dependencies and access the external API)

### Installation
#### Clone the repository:
   ```bash
   git clone git@github.com:jacinside/directa24-back-end-dev-challenge.git
   ```

#### Build the project:
```bash
mvn clean install
```

#### Run the application:

```bash
mvn spring-boot:run
```

## API Documentation
### Swagger UI
The Swagger UI is available at: 
http://localhost:8080/swagger-ui.html

### OpenAPI JSON/YAML
The OpenAPI documentation is available at:
- JSON: http://localhost:8080/v3/api-docs
- YAML: http://localhost:8080/v3/api-docs.yaml


### Running Tests
To run the tests, execute the following command:
```bash 
mvn test
```

## Configuration
### Application Properties 

The application uses the following configuration properties:

| Property                | Description                              | Default Value                     |
|-------------------------|------------------------------------------|-----------------------------------|
| `movies.api.base-url`   | Base URL of the external movies API      | `https://directa24-movies.wiremockapi.cloud/api/movies/search` |
| `server.port`           | Port on which the application runs       | `8080`                           |
| `server.version`        | Version of the application               | `1.0.0`                          |

You can override these properties in the `application.yml` or by passing them as system properties.

## Development Notes

### Key Components
- **`DirectorController`**: Handles REST API requests for fetching directors.
- **`DirectorService`**: Contains the business logic for processing director data.
- **`RestTemplateConfig`**: Configures the `RestTemplate` bean for making HTTP requests.
- **`OpenApiConfig`**: Configures OpenAPI/Swagger for API documentation.

### Logging
The application uses SLF4J with Logback for logging. Logs are categorized as follows:
- **INFO**: General application flow.
- **DEBUG**: Detailed information for debugging purposes.
- **ERROR**: Errors encountered during execution.

### External API
The application fetches movie data from the external API:
https://challenge.iugolabs.com/api/movies/search?page=<pagenumber></pagenumber>

### Error Handling
- If the external API is unreachable, an error is logged, and the process stops.
- Invalid or missing data is handled gracefully with appropriate warnings in the logs.

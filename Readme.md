# Directa24 Back-End Developer Challenge 

In this challenge, the REST API contains information about a collection of movie released after the year 2010, directed by acclaimed directors.  
Given the threshold value, the goal is to use the API to get the list of the names of the directors with most movies directed. Specifically, the list of names of directors with movie count strictly greater than the given threshold.   
The list of names must be returned in alphabetical order.  

To access the collection of users perform HTTP GET request to:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=<pageNumber>
where <pageNumber> is an integer denoting the page of the results to return.

For example, GET request to:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=2
will return the second page of the collection of movies. Pages are numbered from 1, so in order to access the first page, you need to ask for page number 1.
The response to such request is a JSON with the following 5 fields:

- page: The current page of the results  
- per_page: The maximum number of movies returned per page.  
- total: The total number of movies on all pages of the result.  
- total_pages: The total number of pages with results.  
- data: An array of objects containing movies returned on the requested page  

Each movie record has the following schema:  
- Title: title of the movie  
- Year: year the movie was released  
- Rated: movie rating  
- Released: movie release date  
- Runtime: movie duration time in minutes  
- Genre: move genre  
- Director: movie director  
- Writer: movie writers  
- Actors: movie actors  

## Posible Solutions
### Clone repository and complete the Function
  
Complete the function:

    List<String> getDirectors(int threshold)

getDirectors has the following parameter:
- threshold: integer denoting the threshold value for the number movies a person has directed

The function must return a list of strings denoting the name of the directors whose number of movies directed is strictly greater than the given threshold. 
The directors name in the list must be ordered in alphabetical order.


#### Sample Input For Custom Testing
    4  
#### Sample Output
    Martin Scorsese
    Woody Allen
    
The threshold value is 4, so the result must contain directors names with more than 4 movies directed.   
There are 2 such directors and names in the alphabetical order listed in Sample Output.

### Alternative: Spring Boot solution

An alternative solution to clonning the repository and implementing the function can be creating a new Spring Boot project with Rest endpoint:       
```
/api/directors?threshold=X
```

Sample : `/api/directors?threshold=4`

Json response:
```
{  "directors": ["Martin Scorsese","Woody Allen"] }
```

The solution can be shared via .zip file or sharing a github repository url via email. 

#### Evaluation Criteria
- Correctness: Does the component correctly fetch and display movies data from the API?
- Code Quality: Is the code well-structured, organized, and maintainable?
- Java Spring Best Practices: Does the candidate follow Java Spring best practices and principles?
- Bonus Points: Does the candidate implement any of the bonus features or additional
best practices?
- Describe on Readme.md if any improvement was left out and why


# Dev Notes: Directa24 Back-End Developer Challenge

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
   git clone <repository-url>
   cd <repository-folder>
   ```

#### Build the project:
```bash
mvn clean install
```

#### Run the application:

```bash
mvn spring-boot:run
```

#### Access the application
http://localhost:8080.

## API Documentation
### Swagger UI
The Swagger UI is available at: 
- Swagger UI: http://localhost:8080/swagger-ui.html

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
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=<pagenumber></pagenumber>

### Error Handling
- If the external API is unreachable, an error is logged, and the process stops.
- Invalid or missing data is handled gracefully with appropriate warnings in the logs.

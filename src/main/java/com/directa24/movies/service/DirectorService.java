package com.directa24.movies.service;

import com.directa24.movies.model.MovieResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for fetching and processing director data from an external API.
 */
@Service
public class DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorService.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    /**
     * Constructor for DirectorService.
     *
     * @param restTemplate the RestTemplate used for making HTTP requests
     * @param baseUrl the base URL of the external API, injected from the application configuration
     */
    public DirectorService(RestTemplate restTemplate, @Value("${movies.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Fetches a list of directors whose movies appear more than the specified threshold.
     *
     * @param threshold the minimum number of movies a director must have to be included in the result
     * @return a sorted list of director names that meet the threshold criteria
     */
    public List<String> getDirectors(int threshold) {
        logger.info("Fetching directors with a movie count threshold of {}", threshold);

        Map<String, Integer> directorCount = new HashMap<>();
        int page = 1;
        int totalPages;

        do {
            // Build the URL for the current page
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("page", page)
                .toUriString();

            logger.debug("Fetching data from URL: {}", url);

            try {
                // Fetch the movie data from the API
                MovieResponse response = restTemplate.getForObject(url, MovieResponse.class);
                if (response == null || response.getData() == null) {
                    logger.warn("No data found for page {}", page);
                    break;
                }

                totalPages = response.getTotalPages();
                logger.debug("Total pages: {}, Current page: {}", totalPages, page);

                // Count the occurrences of each director
                response.getData().forEach(movie -> {
                    String director = movie.getDirector();
                    int count = directorCount.getOrDefault(director, 0) + 1;
                    directorCount.put(director, count);
                    logger.debug("Director: {}, Current count: {}", director, count);
                });

                page++;
            } catch (HttpServerErrorException e) {
                logger.error("Server error occurred while fetching data from URL: {}. Status code: {}, Response body: {}",
                    url, e.getStatusCode(), e.getResponseBodyAsString(), e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with external API request", e);
            } catch (RestClientException e) {
                logger.error("Error occurred while fetching data from URL: {}", url, e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error with external API request", e);
            } catch (Exception e) {
                logger.error("An unexpected error occurred: {}", e.getMessage(), e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", e);
            }
        } while (page <= totalPages);

        // Filter directors by threshold and return a sorted list
        List<String> filteredDirectors = directorCount.entrySet().stream()
            .filter(entry -> entry.getValue() > threshold)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());

        logger.info("Found {} directors meeting the threshold criteria", filteredDirectors.size());
        logger.debug("Filtered directors: {}", filteredDirectors);

        return filteredDirectors;
    }
}
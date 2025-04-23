package com.directa24.movies.service;

import com.directa24.movies.model.MovieResponse;
import com.directa24.movies.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DirectorServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DirectorService directorService;

    private final String baseUrl = "http://example.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.directorService = new DirectorService(restTemplate, baseUrl);
    }

    @Test
    void getDirectors_Success() {
        // Mock response
        MovieResponse mockResponse = new MovieResponse();
        mockResponse.setTotalPages(1);

        Movie movie1 = new Movie();
        movie1.setDirector("Director A");

        Movie movie2 = new Movie();
        movie2.setDirector("Director B");

        Movie movie3 = new Movie();
        movie3.setDirector("Director A");

        mockResponse.setData(List.of(movie1, movie2, movie3));

        String url = baseUrl + "?page=1";
        when(restTemplate.getForObject(url, MovieResponse.class)).thenReturn(mockResponse);

        // Call the method
        List<String> directors = directorService.getDirectors(1);

        // Verify the result
        assertEquals(List.of("Director A"), directors);

        // Verify interactions
        verify(restTemplate, times(1)).getForObject(url, MovieResponse.class);
    }

    @Test
    void getDirectors_RestClientException() {
        // Mock exception
        String url = baseUrl + "?page=1";
        when(restTemplate.getForObject(url, MovieResponse.class)).thenThrow(new RestClientException("500 Internal Server Error"));

        // Call the method
        List<String> directors = directorService.getDirectors(1);

        // Verify the result
        assertTrue(directors.isEmpty());

        // Verify interactions
        verify(restTemplate, times(1)).getForObject(url, MovieResponse.class);
    }
}
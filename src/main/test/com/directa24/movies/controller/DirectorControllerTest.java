package com.directa24.movies.controller;

import com.directa24.movies.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

    @Test
    void getDirectors_ShouldReturnDirectors() throws Exception {
        // Mock the service response
        when(directorService.getDirectors(4)).thenReturn(List.of("Director A", "Director B"));

        // Perform the GET request and document it
        mockMvc.perform(get("/api/v1/directors")
                .param("threshold", "4")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}
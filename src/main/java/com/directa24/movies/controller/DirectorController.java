package com.directa24.movies.controller;

import com.directa24.movies.model.DirectorsResponse;
import com.directa24.movies.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Director API", description = "API for managing directors (v1)")
@RequestMapping("/api/v1")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @Operation(summary = "Get directors by threshold", description = "Fetches directors with movies above the given threshold")
    @GetMapping("/directors")
    public DirectorsResponse getDirectors(@RequestParam("threshold") int threshold) {
        List<String> directors = directorService.getDirectors(threshold);
        return new DirectorsResponse(directors);
    }
}
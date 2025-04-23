package com.directa24.movies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Model representing the response schema for the directors API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response containing a list of directors")
public class DirectorsResponse {

    @Schema(description = "List of director names", example = "[\"Martin Scorsese\", \"Woody Allen\"]")
    private List<String> directors;
}
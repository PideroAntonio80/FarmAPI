package com.sanvalero.practicadosspringmayo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@NoArgsConstructor
public class LocationDTO {

    @Schema(description = "Location name", example = "Plan", required = true)
    private String name;

    @Schema(description = "Location's population", example = "8500")
    private int population;
}

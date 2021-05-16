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
public class AnimalDTO {

    @Schema(description = "Animal name", example = "cow", required = true)
    private String name;

    @Schema(description = "Animal weight", example = "40", required = true)
    private float weight;
}

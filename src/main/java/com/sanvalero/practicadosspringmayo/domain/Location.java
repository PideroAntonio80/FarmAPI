package com.sanvalero.practicadosspringmayo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "location")
public class Location {

    @Schema(description = "Location identification code", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Location name", example = "Plan", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Location's population", example = "8500")
    @Column
    private int population;

    @Schema(description = "Farms list in this location")
    @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
    private List<Farm> farms;
}

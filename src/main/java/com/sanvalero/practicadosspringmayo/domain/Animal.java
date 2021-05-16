package com.sanvalero.practicadosspringmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animal")
public class Animal {

    @Schema(description = "Animal identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Animal name", example = "cow", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Animal weight", example = "40", required = true)
    @NotBlank
    @Column
    private float weight;

    @Schema(description = "Animal's farm identity")
    @ManyToOne
    @JoinColumn(name = "farm_id")
    @JsonBackReference
    private Farm farm;
}

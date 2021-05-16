package com.sanvalero.practicadosspringmayo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "farm")
public class Farm {

    @Schema(description = "Farm identification number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Farm name", example = "IsaFarm", required = true)
    @NotBlank
    @Column
    private String name;

    @Schema(description = "Farm size in square metres", example = "60000")
    @Column
    private float size;

    @Schema(description = "Farm origin date", example = "08/07/1978")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column
    private LocalDate farmDate;

    @Schema(description = "Farm's Location identity")
    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @Schema(description = "Farm´s animals list")
    @OneToMany(mappedBy = "farm", cascade = CascadeType.REMOVE)
    private List<Animal> animals;
}

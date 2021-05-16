package com.sanvalero.practicadosspringmayo.service;

import com.sanvalero.practicadosspringmayo.domain.Farm;
import com.sanvalero.practicadosspringmayo.domain.dto.FarmDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public interface FarmService {

    Set<Farm> findAll();
    Optional<Farm> findById(long id);

    Farm addFarmToLocation(long id, FarmDTO FarmDTO);
    Farm modifyFarm(long id, FarmDTO FarmDTO);
    void deleteFarm(long id);
}

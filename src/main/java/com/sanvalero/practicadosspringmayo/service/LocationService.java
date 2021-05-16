package com.sanvalero.practicadosspringmayo.service;

import com.sanvalero.practicadosspringmayo.domain.Location;
import com.sanvalero.practicadosspringmayo.domain.dto.LocationDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */
public interface LocationService {

    Set<Location> findAll();
    Optional<Location> findById(long id);

    Location addLocation(Location location);
    Location modifyLocation(long id, LocationDTO locationDTO);
    void deleteLocation(long id);
}

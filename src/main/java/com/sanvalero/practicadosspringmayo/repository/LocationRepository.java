package com.sanvalero.practicadosspringmayo.repository;

import com.sanvalero.practicadosspringmayo.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    Set<Location> findAll();
}

package com.sanvalero.practicadosspringmayo.repository;

import com.sanvalero.practicadosspringmayo.domain.Farm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Repository
public interface FarmRepository extends CrudRepository<Farm, Long> {
    Set<Farm> findAll();
}

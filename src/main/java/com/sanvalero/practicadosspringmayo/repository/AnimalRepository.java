package com.sanvalero.practicadosspringmayo.repository;

import com.sanvalero.practicadosspringmayo.domain.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    Set<Animal> findAll();
}

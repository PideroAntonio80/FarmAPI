package com.sanvalero.practicadosspringmayo.service;

import com.sanvalero.practicadosspringmayo.domain.Animal;
import com.sanvalero.practicadosspringmayo.domain.dto.AnimalDTO;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

public interface AnimalService {

    Set<Animal> findAll();
    Optional<Animal> findById(long id);

    Animal addAnimalToFarm(long id, AnimalDTO animalDTO);
    Animal modifyAnimal(long id, AnimalDTO animalDTO);
    Animal modifyAnimalByWeight(long id, float weight);
    void deleteAnimal(long id);
}

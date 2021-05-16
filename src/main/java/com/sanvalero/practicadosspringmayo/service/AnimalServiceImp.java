package com.sanvalero.practicadosspringmayo.service;

import com.sanvalero.practicadosspringmayo.domain.Animal;
import com.sanvalero.practicadosspringmayo.domain.Farm;
import com.sanvalero.practicadosspringmayo.domain.dto.AnimalDTO;
import com.sanvalero.practicadosspringmayo.exception.AnimalNotFoundException;
import com.sanvalero.practicadosspringmayo.exception.FarmNotFoundException;
import com.sanvalero.practicadosspringmayo.repository.AnimalRepository;
import com.sanvalero.practicadosspringmayo.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Service
public class AnimalServiceImp implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FarmRepository farmRepository;
    
    @Override
    public Set<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> findById(long id) {
        return animalRepository.findById(id);
    }

    @Override
    public Animal addAnimalToFarm(long id, AnimalDTO animalDTO) {
        Animal newAnimal = new Animal();
        setAnimal(newAnimal, animalDTO);
        newAnimal = animalRepository.save(newAnimal);
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));
        newAnimal.setFarm(farm);

        animalRepository.save(newAnimal);

        return newAnimal;
    }

    @Override
    public Animal modifyAnimal(long id, AnimalDTO animalDTO) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
        setAnimal(animal, animalDTO);
        return animalRepository.save(animal);
    }

    @Override
    public Animal modifyAnimalByWeight(long id, float weight) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
        animal.setWeight(weight);
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
        animalRepository.delete(animal);
    }

    public void setAnimal(Animal animal, AnimalDTO animalDTO) {
        animal.setName(animalDTO.getName());
        animal.setWeight(animalDTO.getWeight());
    }
}

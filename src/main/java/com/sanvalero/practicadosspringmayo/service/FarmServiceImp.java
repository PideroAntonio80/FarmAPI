package com.sanvalero.practicadosspringmayo.service;

import com.sanvalero.practicadosspringmayo.domain.Farm;
import com.sanvalero.practicadosspringmayo.domain.Location;
import com.sanvalero.practicadosspringmayo.domain.dto.FarmDTO;
import com.sanvalero.practicadosspringmayo.exception.FarmNotFoundException;
import com.sanvalero.practicadosspringmayo.exception.LocationNotFoundException;
import com.sanvalero.practicadosspringmayo.repository.FarmRepository;
import com.sanvalero.practicadosspringmayo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @ author: Pedro Orós
 * el 16/05/2021
 */

@Service
public class FarmServiceImp implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private LocationRepository locationRepository;
    
    @Override
    public Set<Farm> findAll() {
        return farmRepository.findAll();
    }

    @Override
    public Optional<Farm> findById(long id) {
        return farmRepository.findById(id);
    }

    @Override
    public Farm addFarmToLocation(long id, FarmDTO FarmDTO) {
        Farm newFarm = new Farm();
        setFarm(newFarm, FarmDTO);
        newFarm = farmRepository.save(newFarm);
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
        newFarm.setLocation(location);

        farmRepository.save(newFarm);

        return newFarm;
    }

    @Override
    public Farm modifyFarm(long id, FarmDTO FarmDTO) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));
        setFarm(farm, FarmDTO);
        return farmRepository.save(farm);
    }

    @Override
    public void deleteFarm(long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException(id));
        farmRepository.delete(farm);
    }

    public void setFarm(Farm farm, FarmDTO farmDTO) {
        farm.setName(farmDTO.getName());
        farm.setSize(farmDTO.getSize());
        farm.setFarmDate(farmDTO.getFarmDate());
    }
}

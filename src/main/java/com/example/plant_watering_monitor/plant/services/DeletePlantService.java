package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.exceptions.PlantNotFoundException;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletePlantService implements Command<Integer, Void> {

    PlantRepository plantRepository;

    public DeletePlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        // find first to see if plant exists
        Optional<Plant> plantOptional = plantRepository.findById(id);
        if (plantOptional.isPresent()) {
            plantRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new PlantNotFoundException();
    }
}

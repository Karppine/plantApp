package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.exceptions.PlantNotFoundException;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.UpdatePlantCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePlantService implements Command<UpdatePlantCommand, Plant> {

    private final PlantRepository plantRepository;

    public UpdatePlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<Plant> execute(UpdatePlantCommand command) {
        Optional<Plant> plantOptional = plantRepository.findById(command.getId());
        if (plantOptional.isPresent()) {
            Plant plant = command.getPlant();
            plant.setId(command.getId());

            //changed to spring boot starter validation: defined in the model/plant using @annotations
            //PlantValidator.execute(plant);

            plantRepository.save(plant);
            return ResponseEntity.ok(plant);
        }
        throw new PlantNotFoundException();
    }
}


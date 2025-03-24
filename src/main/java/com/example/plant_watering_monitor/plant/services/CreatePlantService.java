package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePlantService implements Command<Plant, PlantDTO> {

    private final PlantRepository plantRepository;

    public CreatePlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    @Override
    public ResponseEntity<PlantDTO> execute(Plant plant) {

        //changed to spring boot starter validation: defined in the model/plant using @annotations
        //PlantValidator.execute(plant);

        Plant savedPlant = plantRepository.save(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PlantDTO(savedPlant));
    }
}

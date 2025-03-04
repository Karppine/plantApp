package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import com.example.plant_watering_monitor.plant.model.Plant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetPlantService implements Query<Integer, PlantDTO> {

    private final PlantRepository plantRepository;

    public GetPlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<PlantDTO> execute(Integer id) {
        Optional<Plant> plantOptional = plantRepository.findById(id);

        if (plantOptional.isPresent()) {
            return ResponseEntity.ok(new PlantDTO(plantOptional.get()));
        }

        return null;
    }
}

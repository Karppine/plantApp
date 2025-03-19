package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchPlantService implements Query<String, List<PlantDTO>> {

    private final PlantRepository plantRepository;

    public SearchPlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<List<PlantDTO>> execute(String name) {
        // JPA method
        return ResponseEntity.ok(plantRepository.findByNameContaining(name)
                .stream()
                .map(PlantDTO::new)
                .toList());
    }
}

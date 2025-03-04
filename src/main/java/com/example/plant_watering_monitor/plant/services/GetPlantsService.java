package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPlantsService implements Query<Void, List<PlantDTO>> {

    private final PlantRepository plantRepository;

    public GetPlantsService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<List<PlantDTO>> execute(Void input) {
        List<Plant> plants = plantRepository.findAll();
        List<PlantDTO> plantDTOS = plants.stream().map(PlantDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(plantDTOS);
    }
}

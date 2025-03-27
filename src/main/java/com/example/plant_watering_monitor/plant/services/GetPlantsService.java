package com.example.plant_watering_monitor.plant.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPlantsService implements Query<Void, List<Plant>> {

    private final PlantRepository plantRepository;

    public GetPlantsService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public ResponseEntity<List<Plant>> execute(Void input) {
        List<Plant> plants = plantRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(plants);
    }


}

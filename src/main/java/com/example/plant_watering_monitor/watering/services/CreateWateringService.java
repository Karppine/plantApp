package com.example.plant_watering_monitor.watering.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.watering.WateringRepository;
import com.example.plant_watering_monitor.watering.model.Watering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateWateringService implements Command<Watering, Watering> {

    private final WateringRepository wateringRepository;

    public CreateWateringService(WateringRepository wateringRepository) {
        this.wateringRepository = wateringRepository;
    }

    @Override
    public ResponseEntity<Watering> execute(Watering watering) {
        Watering savedWatering = wateringRepository.save(watering);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWatering);
    }
}

package com.example.plant_watering_monitor.watering.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.exceptions.WateringNotFoundException;
import com.example.plant_watering_monitor.watering.WateringRepository;
import com.example.plant_watering_monitor.watering.model.Watering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteWateringService implements Command<Integer, Void> {
    private final WateringRepository wateringRepository;

    public DeleteWateringService(WateringRepository wateringRepository) {
        this.wateringRepository = wateringRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id)  {
        Optional<Watering> wateringOptional = wateringRepository.findById(id);
        if (wateringOptional.isPresent()) {
            wateringRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new WateringNotFoundException();
    }

}

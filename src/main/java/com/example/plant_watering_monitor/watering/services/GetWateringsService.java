package com.example.plant_watering_monitor.watering.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.watering.WateringRepository;
import com.example.plant_watering_monitor.watering.model.Watering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetWateringsService implements Query<Void, List<Watering>> {

    private final WateringRepository wateringRepository;

    public GetWateringsService(WateringRepository wateringRepository) {
        this.wateringRepository = wateringRepository;
    }

    @Override
    public ResponseEntity<List<Watering>> execute(Void input) {
        List<Watering> waterings = wateringRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(waterings);
    }
}


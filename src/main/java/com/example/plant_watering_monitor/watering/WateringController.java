package com.example.plant_watering_monitor.watering;

import com.example.plant_watering_monitor.watering.model.Watering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WateringController {

    private final WateringRepository repo;

    public WateringController(WateringRepository wateringRepository){
        this.repo = wateringRepository;
    }

    @GetMapping("/waterings")
    public ResponseEntity<List<Watering>> getWaterings() {
        var waterings = repo.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(waterings);
    }


}

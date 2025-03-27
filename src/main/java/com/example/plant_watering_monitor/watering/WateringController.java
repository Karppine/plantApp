package com.example.plant_watering_monitor.watering;

import com.example.plant_watering_monitor.watering.model.Watering;
import com.example.plant_watering_monitor.watering.services.CreateWateringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WateringController {

    private final WateringRepository repo;

    private final CreateWateringService createWateringService;

    public WateringController(WateringRepository wateringRepository, CreateWateringService createWateringService){
        this.createWateringService = createWateringService;
        this.repo = wateringRepository;
    }

    @PostMapping("/watering")
    public ResponseEntity<Watering> createWatering(@RequestBody Watering watering) {return createWateringService.execute(watering);}

    @GetMapping("/waterings")
    public ResponseEntity<List<Watering>> getWaterings() {
        var waterings = repo.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(waterings);
    }




}

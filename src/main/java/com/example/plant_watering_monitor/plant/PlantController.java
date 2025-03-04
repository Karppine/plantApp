package com.example.plant_watering_monitor.plant;


import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import com.example.plant_watering_monitor.plant.model.UpdatePlantCommand;
import com.example.plant_watering_monitor.plant.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantController {

    private final CreatePlantService createPlantService;

    private final GetPlantsService getPlantsService;

    private final GetPlantService getPlantService;

    private final UpdatePlantService updatePlantService;

    private final DeletePlantService deletePlantService;

    //constructor injection
    public PlantController(CreatePlantService createPlantService,
                           GetPlantsService getPlantsService,
                           GetPlantService getPlantService,
                           UpdatePlantService updatePlantService,
                           DeletePlantService deletePlantService) {
        this.createPlantService = createPlantService;
        this.getPlantsService = getPlantsService;
        this.getPlantService = getPlantService;
        this.updatePlantService = updatePlantService;
        this.deletePlantService = deletePlantService;
    }


    @PostMapping("/plant")
    public ResponseEntity<PlantDTO> createPlant(@RequestBody Plant plant) {
        return createPlantService.execute(plant);
    }

    @GetMapping("/plants")
    public ResponseEntity<List<PlantDTO>> getPlants() {
        return getPlantsService.execute(null);
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<PlantDTO> getPlantById(@PathVariable Integer id) {
        return getPlantService.execute(id);
    }

    @PutMapping("/plant/{id}")
    public ResponseEntity<PlantDTO> updatePlant(@PathVariable Integer id, @RequestBody Plant plant) {
        // execute only takes one parameter so we use a "container" class to pass two params
        return updatePlantService.execute(new UpdatePlantCommand(id, plant));
    }

    @DeleteMapping("/plant/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Integer id) {
        return deletePlantService.execute(id);
    }

}

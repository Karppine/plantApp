package com.example.plant_watering_monitor.watering;

import com.example.plant_watering_monitor.watering.model.Watering;
import com.example.plant_watering_monitor.watering.services.CreateWateringService;
import com.example.plant_watering_monitor.watering.services.DeleteWateringService;
import com.example.plant_watering_monitor.watering.services.GetWateringsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WateringController {

    private final CreateWateringService createWateringService;

    private final GetWateringsService getWateringsService;

    private final DeleteWateringService deleteWateringService;

    public WateringController(CreateWateringService createWateringService,
                              GetWateringsService getWateringsService,
                              DeleteWateringService deleteWateringService) {
        this.createWateringService = createWateringService;
        this.getWateringsService = getWateringsService;
        this.deleteWateringService = deleteWateringService;
    }

    @PostMapping("/watering")
    public ResponseEntity<Watering> createWatering(@RequestBody Watering watering) {return createWateringService.execute(watering);}

    @GetMapping("/waterings")
    public ResponseEntity<List<Watering>> getWaterings() {
        return getWateringsService.execute(null);
    }

    @DeleteMapping("/watering/{id}")
    public ResponseEntity<Void> deleteWatering(@PathVariable Integer id) {
        return deleteWateringService.execute(id);
    }


}

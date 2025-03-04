package com.example.plant_watering_monitor.plant.model;


/**
 * This class is intended for holding two method arguments instead of one in execute() method from Command interface.
 * Used in PlantController to give extra arguments for UpdatePlantService
 *
*/
//@Getter
public class UpdatePlantCommand {
    private Integer id;
    private Plant plant;

    public UpdatePlantCommand(Integer id, Plant plant) {
        this.id = id;
        this.plant = plant;
    }

    public Integer getId() {
        return id;
    }

    public Plant getPlant() {
        return plant;
    }
}

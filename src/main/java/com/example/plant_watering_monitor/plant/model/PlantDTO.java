package com.example.plant_watering_monitor.plant.model;

import lombok.Data;

// luokan tarkoitus trimmata taulukosta turhia atribuutteja joita ei tarvitse paluattaa http vastauksessa
// hetken oli käytössä mutta revin sen irti jotta @Entity kautta saadaan autobindaukset ui:seen
@Data
public class PlantDTO {
    private Integer id;
    private String name;
    private String description;
    private String last_watered;
    private String last_fertilized;

    public PlantDTO(Plant plant) {
        this.id = plant.getId();
        this.name = plant.getName();
        this.description = plant.getDescription();
        this.last_watered = plant.getLast_watered();
        this.last_fertilized = plant.getLast_fertilized();
    }

    public PlantDTO(){};
}

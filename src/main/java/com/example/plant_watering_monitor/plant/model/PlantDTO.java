package com.example.plant_watering_monitor.plant.model;

// luokan tarkoitus trimmata taulukosta turhia atribuutteja joita ei tarvitse paluattaa http vastauksessa
// @Data lombok broken in intellij
public class PlantDTO {
    private Integer id;
    private String name;

    public PlantDTO(Plant plant) {
        this.id = plant.getId();
        this.name = plant.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

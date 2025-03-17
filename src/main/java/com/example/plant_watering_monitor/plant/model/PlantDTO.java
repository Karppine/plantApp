package com.example.plant_watering_monitor.plant.model;

import java.util.Objects;

// luokan tarkoitus trimmata taulukosta turhia atribuutteja joita ei tarvitse paluattaa http vastauksessa
// @Data lombok broken in intellij
public class PlantDTO {
    private Integer id;
    private String name;
    private String description;

    public PlantDTO(Plant plant) {
        this.id = plant.getId();
        this.name = plant.getName();
        this.description = plant.getDescription();
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantDTO plantDTO = (PlantDTO) o;
        return Objects.equals(id, plantDTO.id) && Objects.equals(name, plantDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

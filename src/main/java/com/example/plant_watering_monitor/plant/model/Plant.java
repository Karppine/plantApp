package com.example.plant_watering_monitor.plant.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // maps java class to mysql
// tämä annotaatio lisäisi getterit ja setterit automaattisesti mutta tässää intellij versiossa se on rikki @Data // lombok library
@Table(name = "plant")
public class Plant {

    @Id //mysql key parameter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Plant() {};

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

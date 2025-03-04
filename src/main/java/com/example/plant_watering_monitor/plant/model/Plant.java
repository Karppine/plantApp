package com.example.plant_watering_monitor.plant.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity // maps java class to mysql
//@Data tämä annotaatio lisäisi getterit ja setterit automaattisesti mutta tässää intellij versiossa se on rikki
@Table(name = "plant")
public class Plant {

    @Id //mysql key parameter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name is required")
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Size(min = 20 , message = ("Description must be over 20 characters long"))
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

package com.example.plant_watering_monitor.plant.model;

import com.example.plant_watering_monitor.watering.model.Watering;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity // maps java class to mysql
@Data
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

    @Size(min = 5 , message = ("Description must be over 20 characters long"))
    @Column(name = "description")
    private String description;

    @Column(name = "last_watered")
    private String last_watered;

    @Column(name = "last_fertilized")
    private String last_fertilized;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Watering> waterings;

    //private String watered;


}


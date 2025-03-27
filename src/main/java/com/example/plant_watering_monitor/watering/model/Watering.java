package com.example.plant_watering_monitor.watering.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "watering")
public class Watering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plantId")
    private Integer plantId;

    @Column(name = "userId")
    private Integer userId;

   /* @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;*/

    @Column(name = "watering_day")
    private LocalDate watering_day;

    @Column(name = "fertilizer")
    private boolean fertilizer;
}

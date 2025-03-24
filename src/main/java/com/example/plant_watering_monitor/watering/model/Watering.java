package com.example.plant_watering_monitor.watering.model;

import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "watering")
public class Watering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
/*

    @Column(name = "plantId")
    private Integer plantId;

    @Column(name = "userId")
    private Integer userId;
*/

    @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;



    @Column(name = "watering_day")
    @Temporal(TemporalType.DATE)
    private java.util.Date Date;

    @Column(name = "fertilizer")
    private boolean bln;
}

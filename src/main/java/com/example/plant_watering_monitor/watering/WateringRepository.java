package com.example.plant_watering_monitor.watering;

import com.example.plant_watering_monitor.watering.model.Watering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WateringRepository extends JpaRepository<Watering, Integer> {
}

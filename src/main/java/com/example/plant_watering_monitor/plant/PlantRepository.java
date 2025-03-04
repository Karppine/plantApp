package com.example.plant_watering_monitor.plant;

import com.example.plant_watering_monitor.plant.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is used in service classes to (constructor-based) dependency inject access to database
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

}

package com.example.plant_watering_monitor.plant;

import com.example.plant_watering_monitor.plant.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class is used in service classes to (constructor-based) dependency inject access to database
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {

    //spring data JPA
    List<Plant> findByNameContaining(String name);

    //JPQL Java Persistence Query Language (
    @Query("SELECT p FROM Plant p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Plant> findByNameOrDescriptionContaining(@Param("keyword") String input);

}

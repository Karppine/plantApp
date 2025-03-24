package com.example.plant_watering_monitor.user;

import com.example.plant_watering_monitor.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

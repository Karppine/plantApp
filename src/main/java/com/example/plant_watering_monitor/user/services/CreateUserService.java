package com.example.plant_watering_monitor.user.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.user.UserRepository;
import com.example.plant_watering_monitor.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements Command<User, User> {

    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> execute(User user) {
        //validation handled in User @Entity using annotations
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}

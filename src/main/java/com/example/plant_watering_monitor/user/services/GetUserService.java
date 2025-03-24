package com.example.plant_watering_monitor.user.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.exceptions.UserNotFoundException;
import com.example.plant_watering_monitor.user.UserRepository;
import com.example.plant_watering_monitor.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserService implements Query<Integer, User> {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> execute(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        throw new UserNotFoundException();
    }
}

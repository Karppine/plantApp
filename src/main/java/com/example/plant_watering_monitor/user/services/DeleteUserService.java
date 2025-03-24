package com.example.plant_watering_monitor.user.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.exceptions.UserNotFoundException;
import com.example.plant_watering_monitor.user.UserRepository;
import com.example.plant_watering_monitor.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserService implements Command<Integer, Void> {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UserNotFoundException();
    }

}

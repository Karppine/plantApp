package com.example.plant_watering_monitor.user.services;

import com.example.plant_watering_monitor.Command;
import com.example.plant_watering_monitor.exceptions.UserNotFoundException;
import com.example.plant_watering_monitor.user.UserRepository;
import com.example.plant_watering_monitor.user.model.UpdateUserCommand;
import com.example.plant_watering_monitor.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserService implements Command<UpdateUserCommand, User> {

    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> execute(UpdateUserCommand command) {
        Optional<User> userOptional = userRepository.findById(command.getId());
        if (userOptional.isPresent()) {
            User user = command.getUser();
            user.setId(command.getId());

            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        throw new UserNotFoundException();
    }
}

package com.example.plant_watering_monitor.user.services;

import com.example.plant_watering_monitor.Query;
import com.example.plant_watering_monitor.user.UserRepository;
import com.example.plant_watering_monitor.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersService implements Query<Void, List<User>> {

    private final UserRepository userRepository;

    public GetUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<User>> execute(Void input) {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}

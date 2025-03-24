package com.example.plant_watering_monitor.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserCommand {
    private Integer id;
    private User user;
}

package com.example.plant_watering_monitor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlantNotFoundException extends RuntimeException{
    public PlantNotFoundException() {
        super(ErrorMessages.PLANT_NOT_FOUND.getMessage());
    }
}

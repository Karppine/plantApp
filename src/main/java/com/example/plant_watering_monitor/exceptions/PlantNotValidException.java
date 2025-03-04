package com.example.plant_watering_monitor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlantNotValidException extends RuntimeException{
    public PlantNotValidException(String message) {
        super(message);
    }
}

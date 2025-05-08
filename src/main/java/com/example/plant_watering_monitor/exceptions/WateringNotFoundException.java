package com.example.plant_watering_monitor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WateringNotFoundException extends RuntimeException {
    public WateringNotFoundException() {
        super(ErrorMessages.WATERING_NOT_FOUND.getMessage());
    }
}

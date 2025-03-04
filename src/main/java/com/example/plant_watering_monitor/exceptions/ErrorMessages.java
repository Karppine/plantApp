package com.example.plant_watering_monitor.exceptions;

public enum ErrorMessages {
    PLANT_NOT_FOUND("plant not found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

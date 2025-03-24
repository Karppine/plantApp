package com.example.plant_watering_monitor.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PLANT_NOT_FOUND("plant not found"),
    USER_NOT_FOUND("user not found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description mus be over 20 characters");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package com.example.plant_watering_monitor.exceptions;

import com.example.plant_watering_monitor.plant.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handlePlantNotFoundException(PlantNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    /*
    Sama kuin yllä mutta ilman annotaatioita
    @ExceptionHandler(PlantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlantNotFoundException(PlantNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
    }
    */

}

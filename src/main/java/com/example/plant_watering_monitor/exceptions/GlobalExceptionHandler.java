package com.example.plant_watering_monitor.exceptions;

import com.example.plant_watering_monitor.plant.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * GlobalExceptionHandler is a centralized error-handling class for the application.
 *
 * <p>This class uses the {@code @ControllerAdvice} annotation, which allows it to
 * handle exceptions thrown by controllers globally. It provides a consistent mechanism
 * to handle errors and return appropriate HTTP status codes and responses.
 *
 * <p>Each method annotated with {@code @ExceptionHandler} handles a specific exception type.
 * When an exception is thrown in a controller, the appropriate handler method is invoked
 * to generate a user-friendly error response.
 *
 * <p>Key annotations used:
 * <ul>
 *   <li>{@code @ControllerAdvice}: Makes this class applicable across all controllers.</li>
 *   <li>{@code @ExceptionHandler}: Marks methods to handle specific exceptions.</li>
 *   <li>{@code @ResponseStatus}: Specifies the HTTP status code to return.</li>
 *   <li>{@code @ResponseBody}: Ensures the error response is returned as JSON or another body format.</li>
 * </ul>
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /*
        Sama kuin alla mutta ilman annotaatioita
        @ExceptionHandler(PlantNotFoundException.class)
        public ResponseEntity<ErrorResponse> handlePlantNotFoundException(PlantNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
        }
    */
    @ExceptionHandler(PlantNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handlePlantNotFoundException(PlantNotFoundException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(PlantNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePlantNotValidException(PlantNotValidException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePlantNotValidConstraints(ConstraintViolationException exception) {
        // just returns the first error, not all of them
        return new ErrorResponse(exception.getConstraintViolations().iterator().next().getMessage());
    }


}

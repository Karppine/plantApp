package com.example.plant_watering_monitor.plant.validators;


import com.example.plant_watering_monitor.exceptions.ErrorMessages;
import com.example.plant_watering_monitor.exceptions.PlantNotValidException;
import com.example.plant_watering_monitor.plant.model.Plant;
import io.micrometer.common.util.StringUtils;

public class PlantValidator {
    private PlantValidator() {

    }

    public static void execute(Plant plant) {
        if (StringUtils.isEmpty(plant.getName())) {
            throw new PlantNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if (plant.getDescription().length() < 20) {
            throw new PlantNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }
    }
}

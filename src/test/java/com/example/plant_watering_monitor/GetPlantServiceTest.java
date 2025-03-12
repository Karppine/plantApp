package com.example.plant_watering_monitor;

import com.example.plant_watering_monitor.exceptions.PlantNotFoundException;
import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import com.example.plant_watering_monitor.plant.services.GetPlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetPlantServiceTest {

    @Mock //what to mock the response fo -> need this dependency to run the test
    private PlantRepository plantRepository;

    @InjectMocks // the thing we are testing
    private GetPlantService getPlantService;

    @BeforeEach // thins we need before the test runs to set up properly
    public void setup() {
        //initializes the repository & the servixe
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_plant_exists_when_get_plant_service_return_plant_dto(){
        //Given
        Plant plant = new Plant();
        plant.setId(1);
        plant.setName("kasvin nimi");
        plant.setDescription("hassu kasvi");

        //this says 'when' but it's actually still setting up
        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        //When
        ResponseEntity<PlantDTO> response = getPlantService.execute(1);

        //Then
        assertEquals(ResponseEntity.ok(new PlantDTO(plant)), response);
        //asserts the product repository was only called once
        verify(plantRepository, times(1)).findById(1);
    }

    @Test
    public void given_plant_does_not_exist_when_get_plant_service_throw_plant_not_found_exeption() {
        //Given
        when(plantRepository.findById(1)).thenReturn(Optional.empty());

        //When & Then
        assertThrows(PlantNotFoundException.class, () -> getPlantService.execute(1));
        //asserts the product repository was only called once
        verify(plantRepository, times(1)).findById(1);
    }

}

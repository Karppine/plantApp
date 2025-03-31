package com.example.plant_watering_monitor.ui;

import com.example.plant_watering_monitor.plant.PlantController;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.watering.WateringController;
import com.example.plant_watering_monitor.watering.model.Watering;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Set;

@SpringComponent
@Scope("prototype")
@Route(value = "" , layout = MainLayout.class)
public class WateringView extends VerticalLayout {
    Grid<Plant> grid = new Grid<>(Plant.class, false);
    Set<Plant> selectedPlants;

    Button waterSelectedPlantsBtn = new Button("Water");
    Checkbox fertilizerUse = new Checkbox("Use fertilizer");


    private final PlantController plantRepo;
    private final WateringController wateringRepo;

    WateringView(PlantController plantRepo, WateringController wateringRepo) {
        this.plantRepo = plantRepo;
        this.wateringRepo = wateringRepo;

        H1 test = new H1("moi");
        add(test);

        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);

        updateList();
    }

    private Component getToolbar() {
        // define watering button
        waterSelectedPlantsBtn.addClickListener(click -> waterSelectedPlants());
        waterSelectedPlantsBtn.setEnabled(false);

        // define fertilize button

        return new HorizontalLayout(waterSelectedPlantsBtn, fertilizerUse);
    }

    // this is probably not error proof
    private void waterSelectedPlants() {
        for (Plant plant : selectedPlants) {
            Watering watering = new Watering();
            watering.setPlantId(plant.getId());
            watering.setUserId(1);
            watering.setWatering_day(LocalDate.now());
            watering.setFertilizer(fertilizerUse.getValue());
            wateringRepo.createWatering(watering);
        }
        updateList();
        grid.deselectAll();
        fertilizerUse.clear();
    }


    private void configureGrid() {
        //grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addColumn(Plant::getName).setHeader("Name").setSortable(true);
        grid.addColumn(Plant::getLast_watered).setHeader("Last watered").setSortable(true);
        grid.addColumn(Plant::getLast_fertilized).setHeader("Last fertilized").setSortable(true);

        grid.addSelectionListener(selection -> {
            selectedPlants = selection.getAllSelectedItems();

            waterSelectedPlantsBtn.setEnabled(!selectedPlants.isEmpty());
        });

        //grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(plantRepo.getPlants().getBody());
    }
}

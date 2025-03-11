package com.example.plant_watering_monitor;


import com.example.plant_watering_monitor.plant.PlantRepository;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;




@Route("")
public class CrudView extends VerticalLayout {

    private final PlantRepository plantRepository;
    final Grid<Plant> grid;

    public CrudView(PlantRepository plantRepository) {
        add(new Button("Click me", e -> Notification.show("Hello, Spring +Vaadin user!!")));

        this.plantRepository = plantRepository;
        this.grid = new Grid<>(Plant.class);
        add(grid);

        listPlants();
    }
    private void listPlants() {
        grid.setItems(plantRepository.findAll());
    }
}

package com.example.plant_watering_monitor.ui;

import com.example.plant_watering_monitor.plant.PlantController;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Route(value = "Plant info", layout = MainLayout.class)
public class PlantView extends VerticalLayout {
    Grid<Plant> grid = new Grid<>(Plant.class);
    PlantForm form;

    PlantController plantRepo;

    public PlantView(PlantController plantRepo) {
        this.plantRepo = plantRepo;

        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new PlantForm();
        form.setWidth("25em");
        form.addSaveListener(this::savePlant); // <1>
        form.addDeleteListener(this::deletePlant); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void savePlant(PlantForm.SaveEvent event) {
        plantRepo.createPlant(event.getPlant());
        updateList();
        closeEditor();
    }

    private void deletePlant(PlantForm.DeleteEvent event) {
        plantRepo.deletePlant(event.getPlant().getId());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("plant-grid");
        grid.setSizeFull();
        grid.setColumns("name", "description", "last_watered", "last_fertilized");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editPlant(event.getValue()));
    }

    private Component getToolbar() {
        Button addPlantButton = new Button("Add plant");
        addPlantButton.addClickListener(click -> addPlant());

        var toolbar = new HorizontalLayout(addPlantButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    public void editPlant(Plant plant) {
        if (plant == null) {
            closeEditor();
        } else {
            form.setPlant(plant);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setPlant(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addPlant() {
        grid.asSingleSelect().clear();
        editPlant(new Plant());
    }


    private void updateList() {
        grid.setItems(plantRepo.getPlants().getBody());
    }
}



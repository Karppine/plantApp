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
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@PermitAll
@Route("")
public class PlantView extends VerticalLayout {
    Grid<Plant> grid = new Grid<>(Plant.class);
    PlantForm form;

    PlantController plantRepo;

    public PlantView(PlantController plantRepo) {
        this.plantRepo = plantRepo;
        addClassName("list-view");
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
        form.addSaveListener(this::saveContact); // <1>
        form.addDeleteListener(this::deleteContact); // <2>
        form.addCloseListener(e -> closeEditor()); // <3>
    }

    private void saveContact(PlantForm.SaveEvent event) {
        plantRepo.createPlant(event.getPlant());
        updateList();
        closeEditor();
    }

    private void deleteContact(PlantForm.DeleteEvent event) {
        plantRepo.deletePlant(event.getPlant().getId());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("name", "description", "last_watered", "last_fertilized");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                editPlant(event.getValue()));
    }

    private Component getToolbar() {
        Button addContactButton = new Button("Add plant");
        addContactButton.addClickListener(click -> addContact());

        var toolbar = new HorizontalLayout(addContactButton);
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

    private void addContact() {
        grid.asSingleSelect().clear();
        editPlant(new Plant());
    }


    private void updateList() {
        grid.setItems(plantRepo.getPlants().getBody());
    }
}



package com.example.plant_watering_monitor.ui;


import com.example.plant_watering_monitor.plant.PlantController;
import com.example.plant_watering_monitor.plant.model.Plant;
import com.example.plant_watering_monitor.plant.model.PlantDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("")
public class CrudView extends VerticalLayout {

    private final PlantController repo;

    private final PlantEditor editor;

    private final Grid<PlantDTO> grid;

    private final Button addNewBtn;

    public CrudView(PlantController repo, PlantEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(PlantDTO.class);
        this.addNewBtn = new Button("New plant", VaadinIcon.PLUS.create());

        //build layout
        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "name", "description");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        //hook to logic components

        // connect selected plant to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editPlant(e.getValue());
        });

        // instantiate and edit new plant when the new button is clicked
        addNewBtn.addClickListener(e -> editor.editPlant(new PlantDTO(new Plant())));

        // listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listPlants();
        });

        // initialize listing
        listPlants();
    }

    private void listPlants() {
        grid.setItems(repo.getPlants().getBody());
    }

}




/*

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
*/

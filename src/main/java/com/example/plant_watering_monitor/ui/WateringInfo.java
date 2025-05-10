package com.example.plant_watering_monitor.ui;

import com.example.plant_watering_monitor.watering.WateringController;
import com.example.plant_watering_monitor.watering.model.Watering;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

import java.util.Set;

@SpringComponent
@Scope("prototype")
@Route(value = "Watering info" , layout = MainLayout.class)
public class WateringInfo extends VerticalLayout {
    Grid<Watering> grid = new Grid<>(Watering.class, false);
    Set<Watering> selectedWaterings;

    Button deleteSelectedWateringsBtn = new Button("Delete");

    private final WateringController wateringRepo;

    WateringInfo(WateringController wateringRepo) {
        this.wateringRepo = wateringRepo;

        H1 header = new H1("Inspect and delete waterings");
        add(header);

        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);

        updateList();
    }

    private Component getToolbar() {
        // define delete watering button
        deleteSelectedWateringsBtn.addClickListener(click -> deleteSelectedWaterings());
        deleteSelectedWateringsBtn.setEnabled(false);

        return new HorizontalLayout(deleteSelectedWateringsBtn);
    }

    // this is probably not error proof
    private void deleteSelectedWaterings() {
        for (Watering watering : selectedWaterings) {
            wateringRepo.deleteWatering(watering.getId());
        }
        updateList();
        grid.deselectAll();
    }


    private void configureGrid() {
        //grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addColumn(watering -> watering.getPlant().getName()).setHeader("Name").setSortable(true);
        grid.addColumn(Watering::getWatering_day).setHeader("Watering day").setSortable(true);
        grid.addColumn(Watering::isFertilizer).setHeader("Last fertilized").setSortable(true);

        grid.addSelectionListener(selection -> {
            selectedWaterings = selection.getAllSelectedItems();

            deleteSelectedWateringsBtn.setEnabled(!selectedWaterings.isEmpty());
        });

        //grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(wateringRepo.getWaterings().getBody());
    }
}

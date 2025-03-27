package com.example.plant_watering_monitor.ui;

import com.example.plant_watering_monitor.plant.model.Plant;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class PlantForm extends FormLayout {
    TextField name = new TextField("Plant name");
    TextField description = new TextField("Plant description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");
    // Other fields omitted
    Binder<Plant> binder = new BeanValidationBinder<>(Plant.class);

    public PlantForm() {
        addClassName("contact-form");
        binder.bindInstanceFields(this);

        add(name, description, createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); // <2>
        close.addClickListener(event -> fireEvent(new CloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean())); // <6>
        }
    }

    public void setPlant(Plant plant) {
        binder.setBean(plant); // <1>
    }

    // Events
    public static abstract class PlantFormEvent extends ComponentEvent<PlantForm> {
        private Plant plant;

        protected PlantFormEvent(PlantForm source, Plant plant) {
            super(source, false);
            this.plant = plant;
        }

        public Plant getPlant() {
            return plant;
        }
    }

    public static class SaveEvent extends PlantFormEvent {
        SaveEvent(PlantForm source, Plant plant) {
            super(source, plant);
        }
    }

    public static class DeleteEvent extends PlantFormEvent {
        DeleteEvent(PlantForm source, Plant plant) {
            super(source, plant);
        }

    }

    public static class CloseEvent extends PlantFormEvent {
        CloseEvent(PlantForm source) {
            super(source, null);
        }
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }
}

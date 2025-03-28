package com.example.plant_watering_monitor.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
@Route("test")
public class WateringView extends VerticalLayout {
    WateringView() {
        H1 test = new H1("moi");
        add(test);
    }
}

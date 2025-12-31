package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;

@Route("whoami")
public class WhoAmIView extends VerticalLayout {

    @PostConstruct
    public void init(){
        Button button = new Button("Who am i?", e -> {
            var description = "???";
            add(new Paragraph(description));
        });

        add(button);
    }
}

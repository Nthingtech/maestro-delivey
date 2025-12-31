package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;

@Route("whoami")
public class WhoAmIView extends VerticalLayout {

    @Inject
    @RestClient
    WhoAmIClient whoami;

    @PostConstruct
    public void init(){

        Button button = new Button("Who am i?", e -> {
            var user = whoami.get();
            var description = "Is anonymous? " + user.isAnonymous;
            if (!user.isAnonymous) {
                description += " " + user.username;
            }
            add(new Paragraph(description));
        });

        add(button);
    }
}

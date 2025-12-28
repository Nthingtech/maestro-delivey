package br.maestro.ren;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class AppContext {

    @Inject
    public AppConfig appConfig;

    public String getTitle() {
        return "Pizzaria";
    }
}

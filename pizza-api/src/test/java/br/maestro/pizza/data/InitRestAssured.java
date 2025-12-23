package br.maestro.pizza.data;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.path.json.config.JsonPathConfig;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;

public class InitRestAssured {
    public void init(@Observes Startup ev) {
        JsonConfig jsonConfig = JsonConfig.jsonConfig()
                .numberReturnType(JsonPathConfig.NumberReturnType.DOUBLE);
        RestAssured.config = RestAssured.config()
                .jsonConfig(jsonConfig);
    }
}

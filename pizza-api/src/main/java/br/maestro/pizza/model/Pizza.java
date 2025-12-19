package br.maestro.pizza.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pizza extends PanacheEntity {

    public String description;

    public Pizza() {
    }

    public static Pizza createPizza(String description) {
        var result = new Pizza();
        result.description = description;
        result.persist();
        return result;
    }

    @Override
    public String toString() {
        return description;
    }
}

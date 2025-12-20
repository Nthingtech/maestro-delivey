package br.maestro.pizza.model;

import br.maestro.pizza.utils.Utils;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Entity
public class Pizza extends PanacheEntity {

    public String name;
    public String description;

    public Pizza() {
    }

    @Transactional
    public static Pizza createPizza(String name, String description) {
        var result = new Pizza();
        result.name = Utils.normalizedName(name);
        result.description = description;
        result.persist();
        return result;
    }

    public String getName() {
        return Utils.toTitleCase(this.name);
    }

    @Override
    public String toString() {
        return description;
    }
}

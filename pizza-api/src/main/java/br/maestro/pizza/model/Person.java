package br.maestro.pizza.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public String email;

    public Person() {}

    @Transactional
    public Person createPerson(String name, String email) {
        var result = new Person();
        result.name = name;
        result.email = email;
        result.persist();
        return result;
    }
}

package br.maestro.pizza;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class PizzaTest {

    @Inject
    PizzaResource pizzaResource;

    @Test
    public void testSanity() {
        List<Pizza> ps = pizzaResource.getPizza();
        assertFalse(ps.isEmpty());
    }
}

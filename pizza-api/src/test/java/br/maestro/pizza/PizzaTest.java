package br.maestro.pizza;

import br.maestro.pizza.model.Location;
import br.maestro.pizza.model.Pizza;
import br.maestro.pizza.model.Store;
import br.maestro.pizza.rs.PizzaResource;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PizzaTest {

    @Inject
    PizzaResource pizzas;

    @BeforeAll
    @Transactional
    public static void beforeAll() {
        QuarkusTransaction.requiringNew().run(() -> {
            var store = new Store();
            store.name = "Pizza Shack";
            store.code = "__default__";
            store.persist();
        });
    }

    /**
     * Initial pizza order happy flow:
     * 1. Show menu of the nearest store
     * 2. Add pizza to cart
     * 3. Review cart before checkout
     * 4. Checkout
     * 5. Delivery
     * 6. Feedback
     */

    @Test
    public void testFindNearestStore() {
        // GIVEN
        var location = Location.current();
        //WHEN
        var store = Store.findNearest(location);
        //THEN
        assertNotNull(store);
        Log.infof(store.id + " " + store.name);
    }

    @Test
    public void testSanity() {
        List<Pizza> ps = pizzas.getPizza();
        assertFalse(ps.isEmpty());
    }
}

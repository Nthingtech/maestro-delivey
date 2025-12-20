package br.maestro.pizza;

import br.maestro.pizza.model.Category;
import br.maestro.pizza.model.Location;
import br.maestro.pizza.model.Person;
import br.maestro.pizza.model.Pizza;
import br.maestro.pizza.model.Store;
import br.maestro.pizza.model.Ticket;
import br.maestro.pizza.rs.PizzaResource;
import io.quarkus.logging.Log;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PizzaTest {

    @Inject
    PizzaResource pizzas;

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
    public void testAddToTicket() {

        // GIVEN
        var store = Store.createStore("Pizza Shack", "__test__");

        var trad = Category.createCategory(store, "Tradicional", "10.99");
        var marg = Pizza.createPizza("Marguerita", "queijo, orégano e tomate.");
        var mush = Pizza.createPizza("Mushrooms", "cogumelos e requeijão cremoso");
        trad.addPizzas(marg, mush);
        var julio = Person.createPerson("Julio", "julio@caravana.cloud", "+55 11 555555");

        // WHEN
        var ticket = Ticket.createTicket(julio, "Av. Mofarrej", "ap 455", "+55 11 845669999");
        ticket.addItem(marg, trad.price, 2);
        ticket.addItem(mush, trad.price, 1);
        var ticketValue = ticket.getValue();

        //THEN
        var expectedValue = new BigDecimal("32.97");
        assertEquals(expectedValue, ticketValue);


    }
}

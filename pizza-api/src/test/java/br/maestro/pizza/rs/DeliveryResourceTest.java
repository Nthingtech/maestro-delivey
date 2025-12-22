package br.maestro.pizza.rs;

import br.maestro.pizza.model.Delivery;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class DeliveryResourceTest {

    @Inject
    Delivery delivery;

    @Test
    public void testUpdateLocation() {
        Log.infof("Testing update for delivery %s", delivery.id);
    }
}
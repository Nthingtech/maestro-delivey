package br.maestro.pizza.rs;

import br.maestro.pizza.model.Pizza;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/pizza")
public class PizzaResource {

    @Transactional
    public void init(@Observes StartupEvent ev) {
        var pizza1 = new Pizza("Bauru");
        pizza1.persist();

        var pizza2 = new Pizza("Quatro queijos");
        pizza2.persist();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pizza> getPizza(){
        List<Pizza> result = Pizza.listAll();
        return result;
    }
}

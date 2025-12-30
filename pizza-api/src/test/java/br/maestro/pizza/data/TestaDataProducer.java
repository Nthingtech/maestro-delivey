package br.maestro.pizza.data;

import br.maestro.pizza.model.Category;
import br.maestro.pizza.model.Courier;
import br.maestro.pizza.model.Delivery;
import br.maestro.pizza.model.Person;
import br.maestro.pizza.model.Pizza;
import br.maestro.pizza.model.Store;
import br.maestro.pizza.model.Ticket;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class TestaDataProducer {

    public Delivery delivery;


    public void init(@Observes StartupEvent ev) {
        Store store = Store.createStore("Testing Store", "__TEST__");

        Category trad = Category.createCategory(store, "Tradicional", "10.99");
        Pizza marg = Pizza.createPizza("Marguerita", "Queijo, Tomate e Orégano");
        Pizza bau = Pizza.createPizza("Bauru", "Queijo, Presunto e Tomate");
        trad.addPizzas(marg, bau);

        Person person = Person.createPerson("Winona Courier", "cool@girl.movie", "55112255555");
        Courier courier = Courier.persist(person.id, "34242334234");

        Ticket ticket = Ticket.createTicket(person, "Rabbit Hole 1", "Tea Room", "11121313121");

        delivery = Delivery.persist(ticket.id, store.id, courier.id);
    }

    @Produces
    public Delivery createDelivery() {
        return delivery;
    }
}

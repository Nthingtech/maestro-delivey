package br.maestro.pizza.dev;

import br.maestro.pizza.model.Category;
import br.maestro.pizza.model.Pizza;
import br.maestro.pizza.model.Store;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class SampleDataInit {

    @Inject
    LaunchMode mode;

    @Transactional
    public void init(@Observes StartupEvent ev) {
        if (LaunchMode.NORMAL.equals(mode))
            return;

        var store = Store.createStore("Pizza Shack", "__default__");

       var trad = Category.createCategory(store, "Tradicional", "10.99");
       var marg = Pizza.createPizza("Marguerita");
       var mush = Pizza.createPizza("Mushrooms");

       trad.addPizzas(marg, mush);

       var premium = Category.createCategory(store, "Premium", "14.99");
       var cheeses = Pizza.createPizza("4 Cheeses");
       var veggies = Pizza.createPizza("Vegetables");
       var napoles = Pizza.createPizza("Napoletana");
       premium.addPizzas(cheeses, veggies, napoles);
    }
}

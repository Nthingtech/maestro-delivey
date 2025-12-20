package br.maestro.pizza.model;

import br.maestro.pizza.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Category extends PanacheEntity {

    public String name;

    @Column(precision = 10, scale = 2)
    public BigDecimal price;

    @JsonIgnore
    @ManyToOne
    public Store store;


    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(name = "pizza_category",
    joinColumns = @JoinColumn(name = "category_id"),
    inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    public List<Pizza> pizzas;

    public Category() {
    }

    @Transactional
    public static Category createCategory(Store store, String name, String price) {
        var result = new Category();
        result.store = store;
        result.name = Utils.normalizedName(name);
        result.price = new BigDecimal(price);
        result.pizzas = new ArrayList<>();
        result.persist();
        return result;
    }

    public void addPizzas(Pizza... ps) {
        Arrays.stream(ps)
                .filter(p -> !this.pizzas.contains(p))
                .forEach(this.pizzas::add);
    }

    public static List<Category> listByStore(Store store) {
        List<Category> result = list("store", Sort.by("price").ascending(), store);
        return  result;
    }

    public String getName() {
        return Utils.toTitleCase(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

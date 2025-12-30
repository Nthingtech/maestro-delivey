package br.maestro.pizza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Entity
public class TicketItem extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    public Ticket ticket;

    @ManyToOne
    public Pizza pizza;

    public BigDecimal price;

    public Integer quantity;

    public TicketItem() {}

    @Transactional
    public static TicketItem createTicketItem(Ticket ticket, Pizza pizza, BigDecimal price, Integer quantity) {
        var result = new TicketItem();
        result.ticket = ticket;
        result.pizza = pizza;
        result.price = price;
        result.quantity = quantity;
        result.persist();
        return result;
    }

    public BigDecimal getValue() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}

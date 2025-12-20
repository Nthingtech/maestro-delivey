package br.maestro.pizza.rs;

import br.maestro.pizza.model.Location;

import java.math.BigDecimal;

public record TicketItemAdd(
        Long pizzaId,
        BigDecimal price,
        Integer quantity
) {
}

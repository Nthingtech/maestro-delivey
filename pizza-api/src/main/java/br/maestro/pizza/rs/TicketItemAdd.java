package br.maestro.pizza.rs;

import java.math.BigDecimal;

public record TicketItemAdd(
        Long pizzaId,
        BigDecimal price,
        Integer quantity
) {
}

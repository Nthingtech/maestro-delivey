package br.maestro.pizza.event;

import br.maestro.pizza.model.Ticket;

import java.time.LocalDateTime;

public record TicketSubmitted(
        Ticket ticket,
        LocalDateTime submittedAt
) {
}

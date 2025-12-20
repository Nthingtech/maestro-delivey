package br.maestro.pizza.model;

import jakarta.persistence.EnumeratedValue;

public enum TicketStatus {
    OPEN("ABERTO"),
    SUBMITTED("ENVIADO"),
    DELETED("DELETADO");

    @EnumeratedValue
    private final String ticketStatus;

    TicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }
}

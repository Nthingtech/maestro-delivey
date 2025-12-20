package br.maestro.pizza.model;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.EnumeratedValue;

public enum TicketStatus {
    OPEN("ABERTO"),
    SUBMITTED("ENVIADO"),
    DELETED("DELETADO");

    @EnumeratedValue
    final String ticketStatus;

    TicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @JsonValue
    public String getTicketStatus() {
        return ticketStatus;
    }
}

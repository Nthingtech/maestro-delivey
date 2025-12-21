package br.maestro.pizza.rs;

import br.maestro.pizza.event.TicketSubmitted;
import br.maestro.pizza.model.Ticket;
import br.maestro.pizza.model.TicketStatus;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.Map;

@Path("/tickets")
@Transactional
public class TicketsResources {

    @Inject
    Event<TicketSubmitted> events;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    // curl -sL -X POST -H 'Content-Type: application/json' -d '{"personId":1, "addressMain":"Rua 1", "addressDetail":"Casa 1", "phone": "123456789"}' http://localhost:8080/api/tickets | jq
    public Ticket createTicket(Map<String , Object> param) {
        Long personId = ((Number) param.get("personId")).longValue();
        String addressMain = (String) param.get("addressMain");
        String addressDetail = (String) param.get("addressDetail");
        String phone = (String) param.get("phone");
        Ticket ticket = Ticket.createTicket(personId, addressMain, addressDetail, phone);
        return ticket;
    }

    @GET
    @Path("/{id}")
    // curl -s http://localhost:8080/api/tickets/1 | jq
    public Ticket readTicket(Long id) {
        return Ticket.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    // curl -s -X DELETE http://localhost:8080/api/tickets/1 | jq
    public Ticket deleteTicket(Long id) {
        Ticket t = readTicket(id);
        t.ticketStatus = TicketStatus.DELETED;
        return t;
    }


    @PUT
    @Path("/{id}")
    // curl -s -X PUT -H "Content-Type: application/json" -d '{"pizzaId": 1, "price":"10.99", "quantity": "2"}' http://localhost:8080/api/tickets/1 | jq
    public Ticket addItem(Long id, TicketItemAdd itemAdd) {
        Ticket ticket = readTicket(id);
        if (ticket == null){
            throw new NotFoundException("Ticket Not Found");
        }
        if (!TicketStatus.OPEN.equals(ticket.ticketStatus)){
            throw new BadRequestException("Ticket not open");
        }
        if (itemAdd.quantity() <= 0
            || itemAdd.quantity() >=99){
            throw new BadRequestException("Invalid quantity");
        }
        ticket.addItem(itemAdd);
        return ticket;
    }

    @POST
    @Path("/{id}/submit")
    // curl -sL -X POST http://locahost:8080/api/tickets/1/submit | jq
    public Ticket submitTicket(Long id) {
        Ticket ticket = readTicket(id);
        if (!TicketStatus.OPEN.equals(ticket.ticketStatus)){
            throw new BadRequestException("Ticket not open");
        }
        ticket.ticketStatus = TicketStatus.SUBMITTED;
        ticket.persistAndFlush();
        events.fireAsync(new TicketSubmitted(
                ticket,
                LocalDateTime.now()
        ));
        return ticket;
    }
}

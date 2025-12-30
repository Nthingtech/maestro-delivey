package br.maestro.pizza.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;

@Entity
@NamedQueries({
        @NamedQuery(name = "Delivery.updateLocation",
                query = """
                    update Delivery d
                        set d.currentLocation = :location
                        where d.id = :id
                    """ ),
        @NamedQuery(name = "Delivery.updateRating",
                query = """
                    update Delivery d
                        set d.rating = :rating
                        where d.id = :id
                    """ ),
})
public class Delivery extends PanacheEntity {

    @ManyToOne
    Ticket ticket;

    @ManyToOne
    public Store store;

    @ManyToOne
    public Courier courier;

    @Embedded
    public Location currentLocation;

    public Integer rating;

    public Delivery() {}

    @Transactional
    public static Delivery persist(Long ticketId, Long storeId, Long courierId) {
        Ticket ticket = Ticket.findById(ticketId);
        Store store = Store.findById(storeId);
        Courier courier = Courier.findById(courierId);
        Delivery result = new Delivery();
        result.ticket = ticket;
        result.store =store;
        result.courier = courier;
        result.persist();
        return result;
    }

    @Transactional
    public static void updateLocation(Long deliveryId, Double lat, Double lon) {
        Location loc = new Location(lat, lon);
        Delivery.update(
                "#Delivery.updateLocation",
                Parameters.with("location", loc)
                        .and("id", deliveryId));
    }

    @Transactional
    public static void updateRating(Long deliveryId, Integer rating) {
        Delivery.update(
                "#Delivery.updateRating",
                Parameters.with("rating", rating)
                        .and("id", deliveryId));
    }
}

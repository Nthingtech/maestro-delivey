package br.maestro.pizza.rs.user;

import io.quarkus.logging.Log;
import jakarta.persistence.ManyToOne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/user/whoami")
public class WhoAmIResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String > getWhoAmI() {
        Map<String, String> result = Map.of();
        Log.infof("WhoAmI() = %s", result);
        return result;
    }
}

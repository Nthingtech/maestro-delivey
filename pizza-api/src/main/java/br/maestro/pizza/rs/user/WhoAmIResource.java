package br.maestro.pizza.rs.user;

import io.quarkus.logging.Log;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.ManyToOne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/user/whoami")
public class WhoAmIResource {

    @Inject
    SecurityIdentity identity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Map<String, String > getWhoAmI() {

        var name = "anonymous";
        if (!identity.isAnonymous()){
            name = identity.getPrincipal().getName();
        }
        Map<String, String> result = Map.of("name", name);
        return result;
    }
}

package br.maestro.pizza.rs.user;

import br.maestro.pizza.auth.UserDetails;
import io.quarkus.logging.Log;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.ManyToOne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;

@Path("/whoami")
public class WhoAmIResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDetails get() {
        return new UserDetails();
    }

}

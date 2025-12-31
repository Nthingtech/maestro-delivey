package br.maestro.pizza.rs.user;

import br.maestro.pizza.auth.UserDetails;
import io.quarkus.logging.Log;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.ManyToOne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Map;

@Path("/whoami")
@Authenticated
public class WhoAmIResource {

    @Inject
    SecurityIdentity id;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDetails get() {
        var name = id.getPrincipal().getName();
        var result = new UserDetails(false, name);
        Log.info(result);
        return result;
    }

}

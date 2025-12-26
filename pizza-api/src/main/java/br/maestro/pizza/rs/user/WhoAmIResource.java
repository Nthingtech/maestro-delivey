package br.maestro.pizza.rs.user;

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

@Path("/user/whoami")
public class WhoAmIResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    UserInfo info;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Map<String, String > getWhoAmI() {
        if (identity.isAnonymous()){
            return Map.of("name", "anonymous");
        }
        var name = info.getName();
        var email = info.getEmail();
        var result = Map.of(
                "name", name,
                "email", email);
        Log.info(result);
        return result;
    }
}

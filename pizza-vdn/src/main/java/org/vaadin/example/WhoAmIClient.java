package org.vaadin.example;

import br.maestro.pizza.auth.UserDetails;
import io.quarkus.oidc.client.filter.OidcClientFilter;
import io.quarkus.oidc.token.propagation.common.AccessToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "whoami")
@AccessToken
public interface WhoAmIClient {

    @GET
    UserDetails get();
}

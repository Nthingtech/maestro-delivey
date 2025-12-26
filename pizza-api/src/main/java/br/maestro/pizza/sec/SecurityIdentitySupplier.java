package br.maestro.pizza.sec;

import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.function.Supplier;

@Dependent
public class SecurityIdentitySupplier implements Supplier<SecurityIdentity> {
    private SecurityIdentity identity;

    @Inject
    JsonWebToken token;

    @Override
    @ActivateRequestContext
    public SecurityIdentity get() {
        if (identity.isAnonymous()){
            return identity;
        }
        var builder = QuarkusSecurityIdentity.builder(identity);
        builder.addRole("user");

        var email = (String) token.getClaim("email");
        if (email != null && email.endsWith("@nthing.com")){
            builder.addRole("admin");
        }
        var result = builder.build();
        return result;
    }

    public void setIdentity(SecurityIdentity identity) {
        this.identity = identity;
    }
}

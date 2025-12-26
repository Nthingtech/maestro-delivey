package br.maestro.ren;

import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/pizzas")
public class PizzasCtrl extends Controller {


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index();
    }

    @GET
    public TemplateInstance index () {
        return Templates.index();
    }
}

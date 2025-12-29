package br.maestro.ren.ctrl;

import br.maestro.ren.AppContext;
import br.maestro.ren.data.IndexData;
import br.maestro.ren.data.SliderItem;
import io.quarkiverse.renarde.Controller;
import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.hibernate.validator.constraints.Length;
import org.jboss.resteasy.reactive.RestForm;

import java.util.List;


public class PizzasCtrl extends Controller {


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(AppContext appContext, IndexData data);
    }

    static List<SliderItem> sliderItems = List.of(
            new SliderItem(
                    "Deliciosa",
                    "Pizza",
                    """
                            Oferecemos uma variedade de pizzas feitas com
                            ingredientes frescos e de alta qualidade.
                            """,
                    "Peça Agora!",
                    "Cardápio",
                    "images/bg_1.png"
            ),
            new SliderItem(
                    "Sensacional",
                    "Calzone",
                    """
                            Crocante por fora, macio por dentro e recheado
                            com uma mistura deliciosa que conquista qualquer
                            paladar.
                            """,
                    "Peça Agora!",
                    "Cardápio",
                    "images/calzone.png"
            ),
            new SliderItem(
                    "Saboroso",
                    "Vinho Artesanal da Casa",
                    """
                            Um vinho artesanal da Maestro, com aroma envolvente
                            e paladar equilibrado,
                            perfeito para harmonizar com nossas pizzas e calzones.
                            """,
                    "Peça Agora!",
                    "Cardápio",
                    "images/vinho_maestro.png"
            ),
            new SliderItem(
                    "Bem-vindo",
                    "Sua pizzaria favorita em São Paulo",
                    """
                            Combinamos a arte da pizza com atendimento de primeira.
                            Experimente nosso calzone, vinho artesanal da casa e descubra
                            por que somos referência na região.
                            """,
                    "Peça Agora!",
                    "Cardápio",
                    null,
                    "background-image: url(images/bg_3.jpg)",
                    true
            )
    );

    @Inject
    AppContext appContext;

    @GET
    @Path("/")
    public TemplateInstance index() {
        return Templates.index(appContext, new IndexData(sliderItems));
    }

    @POST
    public void doSendMessage(
           @NotBlank @Length(min = 2, max = 25) @RestForm String firstName,
           @NotBlank @Length(min = 2, max = 25) @RestForm String lastName,
           @NotBlank @Length(min = 2, max = 25) @RestForm String message
    ) {
        var profanity = message.contains("fuck");
        if (profanity){
            validation.addError("message", "Fala assim não!");
        }

        if(validation.hasErrors()) {
            validation.addError("someError", "Falha ao enviar a Mensagem, verifique a seção Contato");
        }
        if (validationFailed()){

            Log.infof("Validation failed for %s %s: \n %s", firstName, lastName, message);
        }

        Log.infof("Message from %s %s: \n %s", firstName, lastName, message);
        flash("flashMessage", "Tudo Certo! Contato enviado.");
        index();
    }

}

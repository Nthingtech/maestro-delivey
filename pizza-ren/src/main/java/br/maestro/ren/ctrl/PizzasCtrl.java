package br.maestro.ren.ctrl;

import br.maestro.ren.AppContext;
import br.maestro.ren.data.IndexData;
import br.maestro.ren.data.SliderItem;
import io.quarkiverse.renarde.Controller;
import io.quarkus.logging.Log;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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

}

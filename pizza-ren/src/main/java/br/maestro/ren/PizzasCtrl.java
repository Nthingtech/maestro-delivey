package br.maestro.ren;

import br.maestro.ren.data.SliderItem;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;


public class PizzasCtrl extends Controller {


    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<SliderItem> sliderItems);
    }

    static List<SliderItem> sliderItems = List.of(
            new SliderItem(
                    "Deliciosa",
                    "Pizzas",
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
                    "Calzones",
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

    @GET
    @Path("/")
    public TemplateInstance index() {
        return Templates.index(sliderItems);
    }
}

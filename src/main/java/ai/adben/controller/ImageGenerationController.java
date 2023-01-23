package ai.adben.controller;

import ai.adben.client.OpenAIClient;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("image.html")
@Produces(MediaType.TEXT_HTML)
public class ImageGenerationController {

    @Location("image.html")
    Template template;

    @Inject
    OpenAIClient openAIClient;

    @GET
    public TemplateInstance image() {
        String url = openAIClient.generateImage("A surrealistic image in witch A field of wildflowers stretches out as far as the eye can see, each one a different color and shape. In the distance, a massive tree towers over the landscape, its branches reaching up to the sky like tentacles.");
        return template.data("url", url);
    }

}

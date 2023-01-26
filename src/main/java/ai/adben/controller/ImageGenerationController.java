package ai.adben.controller;

import ai.adben.client.OpenAIClient;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
       // Uni<String> result = openAIClient.generateImage("A surrealistic image in witch A field of wildflowers stretches out as far as the eye can see, each one a different color and shape. In the distance, a massive tree towers over the landscape, its branches reaching up to the sky like tentacles.");
        return template.data("url", "result.toString()");
    }

}

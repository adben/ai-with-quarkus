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
@Path("text.html")
@Produces(MediaType.TEXT_HTML)
public class TextGenerationController {

    @Location("text.html")
    Template template;

    @Inject
    OpenAIClient openAIClient;

    @GET
    public TemplateInstance text() {
        String text = openAIClient.generateText("Provide me 3 ideas for side projects on Quarkus");
        return template.data("text", text);
    }

}

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
@Path("text.html")
@Produces(MediaType.TEXT_HTML)
public class TextGenerationController {

    @Location("text.html")
    Template template;

    @Inject
    OpenAIClient openAIClient;

    @GET
    public TemplateInstance text() {
        //String text = openAIClient.generateText("Provide me 3 ideas for side projects on Quarkus");
        return template.data("text", "text");
    }

}

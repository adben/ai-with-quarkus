package ai.adben;

import ai.adben.client.OpenAIClient;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("texts")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class TextGenerationResource {

    @Inject
    OpenAIClient openAIClient;

    @POST
    @Produces(MediaType.TEXT_HTML)
    public Uni<JsonObject> text(final String prompt) {
        return openAIClient.generateText(prompt);
    }

}
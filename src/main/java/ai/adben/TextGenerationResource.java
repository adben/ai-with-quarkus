package ai.adben;

import ai.adben.client.OpenAIClient;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("texts")
@ApplicationScoped
public class TextGenerationResource {

    @Inject
    OpenAIClient openAIClient;

    @GET
    public Uni<JsonObject> text() {
        return openAIClient.generateText("Provide me 3 ideas for side projects on Quarkus, that uses full capabilities of Quarkus reactive APIs");
    }

}
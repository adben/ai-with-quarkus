package ai.adben;

import ai.adben.client.OpenAIClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("texts")
@ApplicationScoped
public class TextGenerationResource {

    @Inject
    OpenAIClient openAIClient;

    @GET
    public String text() {
        return openAIClient.generateText("Provide me 3 ideas for side projects on Quarkus, that uses full capabilities of reactive APIs");
    }

}
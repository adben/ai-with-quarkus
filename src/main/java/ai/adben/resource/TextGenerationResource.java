package ai.adben.resource;

import ai.adben.client.OpenAIClient;
import ai.adben.model.InstructionRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("texts")
@ApplicationScoped
public class TextGenerationResource {

    @Inject
    OpenAIClient openAIClient;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String text(final String prompt) {
        return openAIClient.generateText(prompt).getChoices().get(0).getText();
    }

    @POST
    @Path("/instruction")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String textCompletion(InstructionRequest request) {
        return openAIClient.generateText(request.prompt(), request.instruction()).getChoices().get(0).getText();
    }

}
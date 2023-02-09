package ai.adben.client;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OpenAIClient {

    private static final Logger LOG = Logger.getLogger(OpenAIClient.class);

    //TODO private final List<Model> models;


    @Inject
    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    @Inject
    @ConfigProperty(name = "openai.text.instruction.default")
    String defaultTextInstruction;

    @Inject
    @ConfigProperty(name = "openai.text.model.default")
    String defaultModel;

    @Inject
    @ConfigProperty(name = "openai.image.size.default")
    String imageSize;

    public Uni<JsonObject> generateImage(String prompt) {
        return null;
    }

    public EditResult generateText(String input, String instruction) {
        final OpenAiService service = new OpenAiService(secretKey);
        EditResult result = service.createEdit(EditRequest.builder()
                .model(defaultModel)
                .input(input)
                .instruction(instruction)
                .build());

        LOG.info("Got response" + result);

        return result;
    }

    public EditResult generateText(String input) {
        return this.generateText(input, defaultTextInstruction);
    }
}
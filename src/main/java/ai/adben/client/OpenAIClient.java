package ai.adben.client;

import com.theokanning.openai.service.OpenAiService;

import io.vertx.core.json.Json;

import com.theokanning.openai.edit.EditRequest;
import com.theokanning.openai.edit.EditResult;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OpenAIClient {

    private static final Logger LOG = Logger.getLogger(OpenAIClient.class);

    @ConfigProperty(name = "openai.text.instruction.default")
    String defaultTextInstruction;

    @ConfigProperty(name = "openai.text.model.default")
    String defaultModel;

    @ConfigProperty(name = "openai.image.size.default")
    String imageSize;

    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    public List<Image> generateImage(final String generation) {
        final OpenAiService service = new OpenAiService(secretKey);
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(generation)
                .n(3)
                .size(imageSize)
                .user("testing")
                .build();
        List<Image> result = service.createImage(createImageRequest).getData();
        LOG.info("Got response " + Json.encodePrettily(result));
        return result;
    }

    public EditResult generateText(String input, String instruction) {
        final OpenAiService service = new OpenAiService(secretKey);
        EditResult result = service.createEdit(EditRequest.builder()
                .model(defaultModel)
                .input(input)
                .instruction(instruction)
                .build());
        LOG.info("Got response " + Json.encodePrettily(result));
        return result;
    }

    public EditResult generateText(String input) {
        return this.generateText(input, defaultTextInstruction);
    }
}
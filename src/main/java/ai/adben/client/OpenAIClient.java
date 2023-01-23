package ai.adben.client;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;


@ApplicationScoped
public class OpenAIClient {

    private Client client;
    private WebTarget textCompletionsTarget;
    private WebTarget imageGenerationsTarget;

    private static final Logger LOGGER = Logger.getLogger(OpenAIClient.class.getName());

    @Inject
    @ConfigProperty(name = "openai.secret-key")
    String secretKey;

    @Inject
    @ConfigProperty(name = "openai.text.completion.url")
    String textCompletions;

    @Inject
    @ConfigProperty(name = "openai.text.model")
    String textModel;

    @Inject
    @ConfigProperty(name = "openai.text.tokens")
    int textTokens;

    @Inject
    @ConfigProperty(name = "openai.image.generations.url")
    String imageGenerations;

    @Inject
    @ConfigProperty(name = "openai.image.size")
    String imageSize;

    @PostConstruct
    void initClient() {
        client = ClientBuilder.newClient();
        textCompletionsTarget = client.target(textCompletions);
        imageGenerationsTarget = client.target(imageGenerations);
    }

    public String generateImage(String prompt) {
        JsonObject requestBody = Json.createObjectBuilder()
                .add("prompt", prompt)
                .add("n", 1)
                .add("size", imageSize)
                .build();

        Response response = imageGenerationsTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(requestBody));

        JsonObject jsonObject = response.readEntity(JsonObject.class);
        LOGGER.info("jsonObject = %s".formatted(jsonObject.toString()));
        return jsonObject.getJsonArray("data").getJsonObject(0).getString("url");

    }

    public String generateText(String prompt) {
        JsonObject requestBody = Json.createObjectBuilder()
                .add("model", textModel)
                .add("prompt", prompt)
                .add("max_tokens", textTokens)
                .build();

        Response response = textCompletionsTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .post(Entity.json(requestBody));

        JsonObject jsonObject = response.readEntity(JsonObject.class);
        LOGGER.info("jsonObject = %s".formatted(jsonObject.toString()));

        return jsonObject.getJsonArray("choices").getJsonObject(0)
                .getString("text");

    }

    @PreDestroy
    void close() {
        client.close();
    }

}
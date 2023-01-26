package ai.adben.client;

import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;
import io.vertx.mutiny.ext.web.client.HttpRequest;
import io.vertx.mutiny.ext.web.client.WebClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class OpenAIClient {

    private final WebClient client;

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

    @Inject
    public OpenAIClient(Vertx vertx) {
        this.client = WebClient.create(vertx);
    }

    public Uni<JsonObject> generateImage(String prompt) {
        return client.post(imageGenerations)
                .putHeader(HttpHeaders.AUTHORIZATION.toString(), "Bearer " + secretKey)
                .sendJsonObject(JsonObject.of("prompt", prompt, "n", 1, "size", imageSize))
                .onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject();
                    } else {
                        return new JsonObject()
                                .put("code", resp.statusCode())
                                .put("message", resp.bodyAsString());
                    }
                });

    }

    public Uni<JsonObject> generateText(String prompt) {
        HttpRequest<Buffer> request = client.post(textCompletions);

        final JsonObject jsonObject = JsonObject.of("model", textModel, "prompt", prompt, "max_tokens", textTokens);

        return request
                .putHeader("content-type", MediaType.APPLICATION_JSON_TYPE.toString())
                .putHeader(HttpHeaders.AUTHORIZATION.toString(), "Bearer " + secretKey)
                .sendJsonObject(jsonObject)
                .onItem().transform(resp -> {
                    if (resp.statusCode() == 200) {
                        return resp.bodyAsJsonObject();
                    } else {
                        return new JsonObject()
                                .put("code", resp.statusCode())
                                .put("message", resp.bodyAsString());
                    }
                });
    }

}
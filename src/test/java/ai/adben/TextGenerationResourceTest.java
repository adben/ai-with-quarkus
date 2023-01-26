package ai.adben;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TextGenerationResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .body("Provide me 3 ideas for side projects on Quarkus")
                .when()
                .post("/texts")
                .then()
                .statusCode(200);
    }

}
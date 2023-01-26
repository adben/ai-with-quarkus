package ai.adben;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TextGenerationResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/texts")
                .then()
                .statusCode(500);
    }

}
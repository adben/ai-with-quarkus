package ai.adben.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Disabled
public class TextGenerationResourceTest {

    @Test
    public void testHelloEndpoint() {
        RestAssured.given()
                .body("Provide me 3 ideas for side projects on Quarkus")
                .when()
                .post("/texts")
                .then()
                .statusCode(200);
    }

}
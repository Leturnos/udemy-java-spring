package com.udemy.spring_boot.integrationtests.swagger;

import com.udemy.spring_boot.config.TestConfigs;
import com.udemy.spring_boot.integrationtests.testcontainer.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUIPage() {
	    var bodyContent = given()
            .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();

        assertTrue(bodyContent.contains("Swagger UI"));
    }

}

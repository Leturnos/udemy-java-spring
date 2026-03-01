package com.udemy.spring_boot.integrationtests.controllers.withyaml;

import com.udemy.spring_boot.config.TestConfigs;
import com.udemy.spring_boot.integrationtests.controllers.withyaml.mapper.YAMLMapper;
import com.udemy.spring_boot.integrationtests.dto.BookDTO;
import com.udemy.spring_boot.integrationtests.dto.Wrapper.BookPagedModel;
import com.udemy.spring_boot.integrationtests.testcontainer.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookControllerYamlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static YAMLMapper objectMapper;

    private static BookDTO bookDTO;

    @BeforeAll
    static void setUp() {
        objectMapper = new YAMLMapper();
        bookDTO = new BookDTO();
    }

    @Test
    @Order(1)
    void createTest() throws ParseException {
        mockBook();

        specification = new RequestSpecBuilder()
            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_GOOGLE)
            .setBasePath("/api/book/v1")
            .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        var createdBook = given().config(
                RestAssuredConfig.config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig().
                            encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
            .contentType(MediaType.APPLICATION_YAML_VALUE)
            .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(bookDTO, objectMapper)
            .when()
                .post()
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
            .extract()
                .body()
                    .as(BookDTO.class, objectMapper);

        bookDTO = createdBook;

        assertNotNull(createdBook.getId());
        assertTrue(createdBook.getId() > 0);

        assertEquals("Joshua Bloch", createdBook.getAuthor());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(createdBook.getLaunchDate());
        assertEquals("06/01/2018", formattedDate);
        assertEquals(249.90, createdBook.getPrice());
        assertEquals("Effective Java", createdBook.getTitle());
    }
    
    @Test
    @Order(2)
    void updateTest() {
        bookDTO.setPrice(250.0);

        var createdBook = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(
                                        EncoderConfig.encoderConfig().
                                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
            .contentType(MediaType.APPLICATION_YAML_VALUE)
            .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(bookDTO, objectMapper)
            .when()
                .put()
            .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
            .extract()
                .body()
                .as(BookDTO.class, objectMapper);

        bookDTO = createdBook;

        assertNotNull(createdBook.getId());
        assertTrue(createdBook.getId() > 0);

        assertEquals("Joshua Bloch", createdBook.getAuthor());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(createdBook.getLaunchDate());
        assertEquals("06/01/2018", formattedDate);
        assertEquals(250.0, createdBook.getPrice());
        assertEquals("Effective Java", createdBook.getTitle());
    }

    @Test
    @Order(3)
    void findByIdTest() {

        var createdBook = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(
                                        EncoderConfig.encoderConfig().
                                                encodeContentTypeAs(MediaType.APPLICATION_YAML_VALUE, ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                    .pathParam("id", bookDTO.getId())
                .when()
                    .get("{id}")
                .then()
                    .statusCode(200)
                    .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                    .body()
                .as(BookDTO.class, objectMapper);

        bookDTO = createdBook;

        assertNotNull(createdBook.getId());
        assertTrue(createdBook.getId() > 0);

        assertEquals("Joshua Bloch", createdBook.getAuthor());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(createdBook.getLaunchDate());
        assertEquals("06/01/2018", formattedDate);
        assertEquals(250.0, createdBook.getPrice());
        assertEquals("Effective Java", createdBook.getTitle());
    }

    @Test
    @Order(4)
    void deleteTest() {
        given(specification)
                .pathParam("id", bookDTO.getId())
            .when()
                .delete("{id}")
            .then()
                .statusCode(204);
    }


    @Test
    @Order(5)
    void findAllTest() throws ParseException {

        var response = given(specification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .queryParam("page", 0, "size", 12, "direction", "asc")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(BookPagedModel.class, objectMapper);

        List<BookDTO> book = response.getContent();

        BookDTO bookOne = book.get(0);

        assertNotNull(bookOne.getId());
        assertTrue(bookOne.getId() > 0);

        assertEquals("Viktor Mayer-Schonberger e Kenneth Kukier", bookOne.getAuthor());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(bookOne.getLaunchDate());
        assertEquals("07/11/2017", formattedDate);
        assertEquals(54.0, bookOne.getPrice());
        assertEquals("Big Data: como extrair volume, variedade," +
                " velocidade e valor da avalanche de informação cotidiana", bookOne.getTitle());

        BookDTO bookten = book.get(9);

        assertNotNull(bookten.getId());
        assertTrue(bookten.getId() > 0);

        assertEquals("Crockford", bookten.getAuthor());
        assertEquals("07/11/2017", formattedDate);
        assertEquals(67.0, bookten.getPrice());
        assertEquals("JavaScript", bookten.getTitle());
    }

    private void mockBook() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        bookDTO.setAuthor("Joshua Bloch");
        Date date = sdf.parse("06/01/2018");
        bookDTO.setLaunchDate(date);
        bookDTO.setPrice(249.90);
        bookDTO.setTitle("Effective Java");
    }
}
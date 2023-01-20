package pl.piomin.services.quarkus.person;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import pl.piomin.services.quarkus.person.model.Person;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PersonResourceTest {

    @Test
    public void findAll() {
        given()
          .when().get("/persons")
          .then()
             .statusCode(200)
             .assertThat().body("size()", is(20));
    }

    @Test
    public void findById() {
        Person person = given()
                .when().get("/persons/1")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Person.class);
        assertNotNull(person);
        assertEquals(1L, person.id);
    }

}
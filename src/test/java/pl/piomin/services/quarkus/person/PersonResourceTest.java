package pl.piomin.services.quarkus.person;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.piomin.services.quarkus.person.model.Address;
import pl.piomin.services.quarkus.person.model.Gender;
import pl.piomin.services.quarkus.person.model.Person;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonResourceTest {

    @Test
    @Order(2)
    public void findAll() {
        given()
          .when().get("/persons")
          .then()
             .statusCode(200)
             .assertThat().body("size()", greaterThan(0));
    }

    @Test
    @Order(2)
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

    @Test
    @Order(2)
    void getPersonByName() {
        Person[] persons = given()
                .pathParam("name", "TestNew")
                .when().get("/persons/name/{name}")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Person[].class);
        assertNotNull(persons);
        assertTrue(persons.length > 0);
    }

    @Test
    @Order(1)
    void newPersonAdd() {
        Person newPerson = new Person();
        newPerson.age = 22;
        newPerson.name = "TestNew";
        newPerson.gender = Gender.FEMALE;
        newPerson.address = new Address();
        Person person = given()
                .body(newPerson)
                .contentType(ContentType.JSON)
                .when().post("/persons")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Person.class);
        assertNotNull(person);
        assertNotNull(person.id);
    }

}
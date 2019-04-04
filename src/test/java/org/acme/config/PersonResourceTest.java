package org.acme.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.test.common.QuarkusTestResource;
//import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonResourceTest {


    @BeforeEach
    public void init() {
        Person person = new Person();
        person.name = "Yoda";
        person.birth = LocalDate.now().minusYears(300);
        person.persist();
    }

    @Test
    public void testListEndPoint() {
        given()
            .when().get("/persons")
            .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .body("size()", is(2))
                .body("name", hasItems("Yoda"))
        ;
    }


}

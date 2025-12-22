package tests;

import model.CreateUserBodyModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PetstoreExtendedTests {

    @Test
    void createUserTest() {
        //String data =  "[{\"id\":777,\"username\":\"Kimo\",\"firstName\":\"Ekaterina\",\"lastName\":\"Kimo\",\"email\":\"12345@gmail.com\",\"password\":\"123\",\"phone\":\"12345678\",\"userStatus\":10}]";
        CreateUserBodyModel body= new CreateUserBodyModel();
        body.setId(777);
        body.setUsername("Kimo");
        body.setFirstName("Ekaterina");
        body.setLastName("Kimo");
        body.setEmail("12345@gmail.com");
        body.setPassword("123");
        body.setPhone("12345678");
        body.setUserStatus(10);



        given()
                .log().uri()
                .contentType(JSON)
                .body(List.of(body))   // ← квадратные скобки
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("message", is("ok"));
    }
}
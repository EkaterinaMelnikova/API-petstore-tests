package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import model.lombok.CreateUserResponseLombokModel;
import model.pojo.CreateUserBodyLombokModel;
import model.pojo.CreateUserResponseModel;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.CreateUserSpec.*;

public class PetstoreExtendedTests {


    @Test
    void createUserLombokTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setId(777);
        body.setUsername("Kimo");
        body.setFirstName("Ekaterina");
        body.setLastName("Kimo");
        body.setEmail("12345@gmail.com");
        body.setPassword("123");
        body.setPhone("12345678");
        body.setUserStatus(10);


        CreateUserResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .contentType(JSON)
                .body(List.of(body))   // ← квадратные скобки
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(CreateUserResponseLombokModel.class);


        assertThat(response.getCode()).isEqualTo(200); // assertj модно-молодежно, легче читать
        assertThat(response.getMessage()).isEqualTo("ok");
        assertThat(response.getType()).isEqualTo("unknown");

    }

    @Test
    void createUserLombokStepsTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setId(777);
        body.setUsername("Kimo");
        body.setFirstName("Ekaterina");
        body.setLastName("Kimo");
        body.setEmail("12345@gmail.com");
        body.setPassword("123");
        body.setPhone("12345678");
        body.setUserStatus(10);


        CreateUserResponseLombokModel response =
                step("Get user data", () ->
                        given()
                                .filter(new AllureRestAssured())
                                .log().uri()
                                .contentType(JSON)
                                .body(List.of(body))   // ← квадратные скобки
                                .when()
                                .post("https://petstore.swagger.io/v2/user/createWithList")
                                .then()
                                .log().status()
                                .log().body()
                                .statusCode(200)
                                .extract().as(CreateUserResponseLombokModel.class));

        step("Verify response", () -> {
            assertThat(response.getCode()).isEqualTo(200);
            assertThat(response.getMessage()).isEqualTo("ok");
            assertThat(response.getType()).isEqualTo("unknown");
        });
    }

    @Test
    void createUserLombokSpecTest() {
        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
        body.setId(777);
        body.setUsername("Kimo");
        body.setFirstName("Ekaterina");
        body.setLastName("Kimo");
        body.setEmail("12345@gmail.com");
        body.setPassword("123");
        body.setPhone("12345678");
        body.setUserStatus(10);


        CreateUserResponseLombokModel response = given(createUserRequestSpec)
                .body(List.of(body))   // ← квадратные скобки
                .when()
                .post("/createWithList")
                .then()
                .spec(createUserResponseSpec)
                .statusCode(200)
                .extract().as(CreateUserResponseLombokModel.class);

        assertThat(response.getCode()).isEqualTo(200); // assertj модно-молодежно, легче читать
        assertThat(response.getMessage()).isEqualTo("ok");
        assertThat(response.getType()).isEqualTo("unknown");

    }
}
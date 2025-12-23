package tests;

import model.lombok.CreateUserResponseLombokModel;
import model.pojo.CreateUserBodyLombokModel;
import model.pojo.CreateUserResponseModel;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class PetstoreExtendedTests {


    @Test
    void createUserLombokTest() {
        //String data =  "[{\"id\":777,\"username\":\"Kimo\",\"firstName\":\"Ekaterina\",\"lastName\":\"Kimo\",\"email\":\"12345@gmail.com\",\"password\":\"123\",\"phone\":\"12345678\",\"userStatus\":10}]";
        CreateUserBodyLombokModel body= new CreateUserBodyLombokModel();
        body.setId(777);
        body.setUsername("Kimo");
        body.setFirstName("Ekaterina");
        body.setLastName("Kimo");
        body.setEmail("12345@gmail.com");
        body.setPassword("123");
        body.setPhone("12345678");
        body.setUserStatus(10);


        CreateUserResponseLombokModel response = given()
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
}
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PetstoreTests {

       /*
        1. Make POST request to https://petstore.swagger.io/v2/user/createWithList
            with body   {
    "id": 777,
    "username": "Kimo",
    "firstName": "Ekaterina",
    "lastName": "Kimo",
    "email": "12345@gmail.com",
    "password": "123",
    "phone": "12345678",
    "userStatus": 10
  }
        2. Get response { "code": 200 }
        3. Check statusCode is 200
           Check message is ok

     */

    @Test
    void createUserTest() {
        String data = "[\n" +
                "  {\n" +
                "    \"id\": 777,\n" +
                "    \"username\": \"Kimo\",\n" +
                "    \"firstName\": \"Ekaterina\",\n" +
                "    \"lastName\": \"Kimo\",\n" +
                "    \"email\": \"12345@gmail.com\",\n" +
                "    \"password\": \"123\",\n" +
                "    \"phone\": \"12345678\",\n" +
                "    \"userStatus\": 10\n" +
                "  }\n" +
                "]";

        given()
                .log().uri()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("message", is("ok"));
    }

    @Test
    void createUserWithoutBodyTest() {
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(405)
                .body("message", is("no data"));
    }

    @Test
    void createUserWithWrongBodyTest() {
        given()
                .log().uri()
                .contentType(JSON)
                .body("123")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(500)
                .body("message", is("something bad happened"));

    }

    @Test
    void createUserWithoutContentTypeTest() {
        String data = "[\n" +
                "  {\n" +
                "    \"id\": 777,\n" +
                "    \"username\": \"Kimo\",\n" +
                "    \"firstName\": \"Ekaterina\",\n" +
                "    \"lastName\": \"Kimo\",\n" +
                "    \"email\": \"12345@gmail.com\",\n" +
                "    \"password\": \"123\",\n" +
                "    \"phone\": \"12345678\",\n" +
                "    \"userStatus\": 10\n" +
                "  }\n" +
                "]";

        given()
                .log().uri()
                .body(data)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

}
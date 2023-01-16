package apiEndpoints;

import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON) //ovo je za header zahteva
                .body(payload)
                .when()
                .post(Routes.user_post_url);
        return response;
    }

    public static Response readUser(String username){
        Response response =given()
                .pathParam("username", username)
                .when()
                .get(Routes.user_get_url);
        return response;
    }

    public static Response updateUser(String username, User payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(Routes.user_put_url);
        return response;
    }

    public static Response deleteUser(String username){
        Response response =given()
                .pathParam("username", username)
                .when()
                .delete(Routes.user_delete_url);
        return response;
    }
}

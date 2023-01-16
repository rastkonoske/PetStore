package apiEndpoints;

import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;
import static io.restassured.RestAssured.given;

//Upotreba ResourceBundle klase kako bi se preuzeli podaci sa .properties fajla

public class UserEndPoints2 {

    static ResourceBundle getURL () {

        ResourceBundle routes = ResourceBundle.getBundle("routes"); //Koristi .properties fajl
        return routes;
    }

    public static Response createUser(User payload){
        String post_url = getURL().getString("post_url");
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON) //ovo je za header zahteva
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response readUser(String username){
        String get_url = getURL().getString("get_url");
        Response response =given()
                .pathParam("username", username)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(String username, User payload){
        String update_url = getURL().getString("update_url");
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(update_url);
        return response;
    }

    public static Response deleteUser(String username){
        String delete_url = getURL().getString("delete_url");
        Response response =given()
                .pathParam("username", username)
                .when()
                .delete(delete_url);
        return response;
    }
}

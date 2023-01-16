package apiEndpoints;

import apiPayload.Store;
import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreEndPoints {

    public static Response createOrder(Store payload){
        Response response =given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON) //ovo je za header zahteva
                .body(payload)
                .when()
                .post(Routes.store_post_url);
        return response;
    }

    public static Response readOrder(int orderId){
        Response response =given()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.store_get_url);
        return response;
    }


    public static Response deleteOrder(int orderId){
        Response response =given()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.store_delete_url);
        return response;
    }
}

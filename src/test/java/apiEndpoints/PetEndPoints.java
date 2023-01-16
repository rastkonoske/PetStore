package apiEndpoints;

import apiPayload.Pet;
import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response createPet(Pet payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON) //ovo je za header zahteva
                .body(payload)
                .when()
                .post(Routes.pet_post_url);
    }

    public static Response readPet(int petId){
        return given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON)
                .pathParam("petId", petId ) //petID //"9223372036854705970"
                .when()
                .get(Routes.pet_get_url);
    }

    public static Response updatePet(int petId, Pet payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("petId", petId)
                .when()
                .put(Routes.pet_put_url);
    }

    public static Response deletePet(int petId){
        return given()
                .pathParam("petId", petId)
                .when()
                .delete(Routes.pet_delete_url);
    }
}

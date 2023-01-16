package apiTest;

import apiEndpoints.PetEndPoints;
import apiEndpoints.UserEndPoints;
import apiPayload.Category;
import apiPayload.Pet;
import apiPayload.Tags;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetTest {

    Faker faker;
    Pet petPayload;
    Category categoryPayload;
    Tags tagsPayload;

    @BeforeClass
    public void setup() {

        faker=new Faker();
        petPayload = new Pet();
        categoryPayload = new Category();
        tagsPayload = new Tags();

        petPayload.setPetId(faker.idNumber().hashCode());
        petPayload.setName(faker.name().firstName());
        petPayload.setPhotoURLS(faker.internet().image());
        petPayload.setStatus(faker.bothify("available"));
        categoryPayload.setId(faker.idNumber().hashCode());
        categoryPayload.setName(faker.name().firstName());
        petPayload.getCategory();
        tagsPayload.setId(faker.idNumber().hashCode());
        tagsPayload.setName(faker.name().firstName());
        petPayload.getTags();

    }

    @Test(priority = 1)
    public void testPostPet() {
        Response response = PetEndPoints.createPet(petPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test (priority = 2)
    public void getPetById () {

        Response response = PetEndPoints.readPet(this.petPayload.getPetId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

//    @Test (priority = 3)
//    public void updatePetByID()
//    {
//
//        //update data using payload
//        petPayload.setName(faker.name().firstName());
//
//        Response response=PetEndPoints.updatePet(Pet.getPetId(),petPayload);
//        response.then().log().body();
//
//        Assert.assertEquals(response.getStatusCode(),200);
//
//        //Checking data after update
//        Response responseAfterUpdate=PetEndPoints.readPet(Pet.getPetId());
//        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
//
//    }
//
//    @Test(priority=4)
//    public void deletePetById()
//    {
//
//        Response response=PetEndPoints.deletePet(this.petPayload.getPetId());
//        Assert.assertEquals(response.getStatusCode(),200);
//
//    }

}

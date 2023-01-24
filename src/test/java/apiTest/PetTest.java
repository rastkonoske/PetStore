package apiTest;

import apiEndpoints.PetEndPoints;
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

        petPayload.setPetId(12);
        petPayload.setName(faker.name().firstName());
        petPayload.setStatus(faker.bothify("available"));
        categoryPayload.setId(5);
        categoryPayload.setName(faker.name().firstName());
        tagsPayload.setId(faker.idNumber().hashCode());
        tagsPayload.setName(faker.name().firstName());

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

    @Test (priority = 3)
    public void updatePetByName()
    {
        //update data using payload
        petPayload.setName("Dzeki");

        Response response=PetEndPoints.updatePet(this.petPayload.getPetId(),petPayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);

        //Checking data after update
        Response responseAfterUpdate=PetEndPoints.readPet(this.petPayload.getPetId());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

    }

    @Test(priority=4)
    public void deletePetById()
    {

        Response response=PetEndPoints.deletePet(this.petPayload.getPetId());
        Assert.assertEquals(response.getStatusCode(),200);

    }

}

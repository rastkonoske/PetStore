package apiTest;

import apiEndpoints.UserEndPoints2;
import apiPayload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest2 {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setup() {

        Faker faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(8,15));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser() {

        logger.info("******************* Creating user *********************");
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******************* User created! *********************");
    }

    @Test (priority = 2)
    public void getUserByName () {

        logger.info("******************* Reading user info *********************");
        Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******************* User info read! *********************");
    }

    @Test(priority=3)
    public void updateUserByName()
    {
        logger.info("******************* Updating user info *********************");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response= UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);

        //Checking data after update
        Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        logger.info("******************* User info updated! *********************");

    }

    @Test(priority=4)
    public void deleteUserByName()
    {

        logger.info("******************* You are about to delete user *********************");
        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("******************* User deleted! *********************");

    }
}

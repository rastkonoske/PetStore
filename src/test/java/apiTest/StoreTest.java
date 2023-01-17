package apiTest;

import apiEndpoints.PetEndPoints;
import apiEndpoints.StoreEndPoints;
import apiPayload.Store;
import apiPayload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoreTest {

    Faker faker;
    Store storePayload;

    @BeforeClass
    public void setup(){

        faker=new Faker();
        storePayload = new Store();

        storePayload.setOrderId(7);
        storePayload.setPetId(12);
        storePayload.setQuantity(faker.hashCode());
        storePayload.setShipDate(faker.date().birthday());
        storePayload.setStatus(faker.bothify("placed"));
        storePayload.setComplete(true);
    }

    @Test(priority = 1)
    public void testPostOrder() {
        Response response = StoreEndPoints.createOrder(storePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test (priority = 2)
    public void getOrderById () {

        Response response = StoreEndPoints.readOrder(this.storePayload.getOrderId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority=3)
    public void deleteOrderById()
    {

        Response response=StoreEndPoints.deleteOrder(this.storePayload.getOrderId());
        Assert.assertEquals(response.getStatusCode(),200);

    }
}

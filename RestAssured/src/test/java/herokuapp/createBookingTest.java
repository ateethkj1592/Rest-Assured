package herokuapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.example.herokuapp.BaseTest.createBooking;

public class createBookingTest {
    @Test
    public void createBookingTest(){

        Response response = createBooking();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(actualFirstName,"Ateeth","firstname is not the expected one");

        String actualLastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName,"K J","lastname is not expected one");

        int price= response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(price,150,"price expected is different");

        boolean depositpaid= response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositpaid,"deposit should be false");

        softAssert.assertAll();



    }


}

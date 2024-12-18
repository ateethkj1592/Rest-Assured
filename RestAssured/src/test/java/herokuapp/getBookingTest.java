package herokuapp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class getBookingTest {

    @Test
    public void getBookingTest() {
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/5");
        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName,"Eric","firstname is not the expected one");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName,"Smith","lastname is not expected one");

        int price= response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price,342,"price expected is different");

        boolean depositpaid= response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositpaid,"deposit should be true");

        softAssert.assertAll();





    }
}

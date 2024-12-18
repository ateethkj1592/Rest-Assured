package herokuapp;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class getBookingIdsTests {

    @Test
    public void getBookingIdsWithoutFilterTest(){
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        Assert.assertEquals(response.getStatusCode(),200,"Status code should be 200");

        List<Integer> bookingIds= response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(),"List of booking id is empty");
    }
}

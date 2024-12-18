package herokuapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.delete;
import static org.example.herokuapp.BaseTest.createBooking;

public class deleteBookingTest
{

    @Test
    public void deleteBookingTest(){

        Response createResponse = createBooking();
        Assert.assertEquals(createResponse.getStatusCode(), 200, "Booking creation failed!");


        int bookingId = createResponse.jsonPath().getInt("bookingid");
        Assert.assertNotNull(bookingId, "Booking ID should not be null");

        Response deleteResponse = RestAssured.given()
                .auth().preemptive().basic("admin", "password123") // Basic authentication
                .contentType(ContentType.JSON)
                .delete("https://restful-booker.herokuapp.com/booking/" + bookingId);


        deleteResponse.print();
        Assert.assertEquals(deleteResponse.getStatusCode(), 201, "Delete booking failed!");


        Response getResponse = RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking/" + bookingId);

        Assert.assertEquals(getResponse.getStatusCode(), 404, "Booking was not deleted successfully!");
    }
}


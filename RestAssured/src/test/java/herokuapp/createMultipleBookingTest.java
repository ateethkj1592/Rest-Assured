package herokuapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.herokuapp.BaseTest;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;

public class createMultipleBookingTest extends BaseTest {


    @Test
    public void createMultipleBookingsTest() {
        Faker faker = new Faker();

        for (int i = 0; i < 10; i++) { // Number of bookings to create
            // Generate dynamic booking details
            JSONObject bookingDetails = new JSONObject();
            bookingDetails.put("firstname", faker.name().firstName());
            bookingDetails.put("lastname", faker.name().lastName());
            bookingDetails.put("totalprice", faker.number().numberBetween(100, 1000));
            bookingDetails.put("depositpaid", faker.bool().bool());

            JSONObject bookingDates = new JSONObject();
            bookingDates.put("checkin", "2024-12-20"); // Static or dynamically generated dates
            bookingDates.put("checkout", "2024-12-25");
            bookingDetails.put("bookingdates", bookingDates);

            bookingDetails.put("additionalneeds", "Breakfast");

            // Call the createBooking method
            Response response = createBooking(bookingDetails.toString());

            // Optionally, verify or log the response
            System.out.println("Booking created with ID: " + response.jsonPath().getInt("bookingid"));
        }
    }

    public static Response createBooking(String bookingDetailsJson) {
        // Send POST request with JSON body
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingDetailsJson)
                .post("https://restful-booker.herokuapp.com/booking");

        response.print();
        return response;
    }
}



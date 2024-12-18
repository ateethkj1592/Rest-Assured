package org.example.herokuapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    public static Response createBooking() {
        String filePath = "src/main/java/org/example/herokuapp/bookingDetails.json"; // Adjust path as needed

        String body;
        try {
            // Read JSON file into a String
            body = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read booking details file", e);
        }

        // Send POST request with JSON body
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("https://restful-booker.herokuapp.com/booking");

        response.print();
        return response;
    }
}

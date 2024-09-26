package main;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiRequestPage {

    private final String baseUrl = "https://postman-echo.com";

    public ApiRequestPage() {
        RestAssured.baseURI = baseUrl;
    }

    // GET-запрос с параметрами
    public Response sendGetRequest(Map<String, String> queryParams) {
        return RestAssured.given()
                .queryParams(queryParams)
                .when()
                .get("/get");
    }

    // POST-запрос с raw текстом
    public Response sendPostRawTextRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .post("/post");
    }

    // POST-запрос с form data
    public Response sendPostFormDataRequest(Map<String, String> formParams) {
        return RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams)
                .when()
                .post("/post");
    }

    // PUT-запрос
    public Response sendPutRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .put("/put");
    }

    // PATCH-запрос
    public Response sendPatchRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .patch("/patch");
    }

    // DELETE-запрос
    public Response sendDeleteRequest(String requestBody) {
        return RestAssured.given()
                .header("Content-Type", "text/plain")
                .body(requestBody)
                .when()
                .delete("/delete");
    }
}
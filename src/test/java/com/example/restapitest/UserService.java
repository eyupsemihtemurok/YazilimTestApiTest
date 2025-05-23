package com.example.restapitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserService {
    private static final String BASE_URI = "https://reqres.in";
    private static final String API_KEY = "reqres-free-v1";

    public static Response createUser(UserModel user) {
        String requestBody = String.format("""
        {
            "name": "%s",
            "email": "%s"
        }
        """, user.getName(), user.getEmail());

        System.out.println("POST is sending to: " + BASE_URI + "/api/users");

        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/api/users")
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post();
    }

    public static Response deleteUser(int userId) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/api/users/" + userId)
                .header("x-api-key", API_KEY)
                .log().all()
                .when()
                .delete();
    }

    public static Response getUser(int userId) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/api/users/" + userId)
                .header("x-api-key", API_KEY)
                .log().all()
                .when()
                .get();
    }

    public static Response listUsers(int page) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/api/users")
                .header("x-api-key", API_KEY)
                .queryParam("page", page)
                .log().all()
                .when()
                .get();
    }
}

package com.spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {
    public static Response post(String path, String token, Object bodyObjectType) {
        return given(getRequestSpec()).
                body(bodyObjectType).
                auth().oauth2(token).
                //header("Authorization", "Bearer " + token).
                        when().
                post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        Response response = given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).
                extract().response();
        return response;
    }

    public static Response get(String path, String token) {
        return given(getRequestSpec()).
                auth().oauth2(token).
                //header("Authorization", "Bearer " + token).
                        contentType(ContentType.JSON).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }

    public static Response update(String path, String token, Object bodyObjectType) {
        return given(getRequestSpec()).
                auth().oauth2(token).
                //header("Authorization", "Bearer " + token).
                        body(bodyObjectType).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().response();
    }
}

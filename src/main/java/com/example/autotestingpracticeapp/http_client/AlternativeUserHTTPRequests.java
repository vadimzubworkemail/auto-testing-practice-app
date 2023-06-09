package com.example.autotestingpracticeapp.http_client;

import com.example.autotestingpracticeapp.model_pojo.UserInput;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class AlternativeUserHTTPRequests extends BaseHTTPClient {

    public Response doGetUsers() {
        return given().spec(getBaseSpec()).get(EndPoints.userEndPoint);
    }

    public Response doGetUsers(UUID id) {
        return given().spec(getBaseSpec()).get(EndPoints.userEndPoint + id);
    }

    public Response doPostUsers(UserInput input) {
        return given().spec(getBaseSpec()).body(input).post(EndPoints.userEndPoint);
    }

    public Response doPutUsers(UUID id, UserInput input) {
        return given().spec(getBaseSpec()).body(input).put(EndPoints.userEndPoint + id);
    }

    public ValidatableResponse doDeleteUsers(UUID id) {
        return given().spec(getBaseSpec()).delete(EndPoints.userEndPoint + id).then().statusCode(HttpStatus.SC_OK);
    }
}

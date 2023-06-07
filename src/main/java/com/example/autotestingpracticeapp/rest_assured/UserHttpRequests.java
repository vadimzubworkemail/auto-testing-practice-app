package com.example.autotestingpracticeapp.rest_assured;

import com.example.autotestingpracticeapp.pojo.UserInput;
import io.restassured.response.ValidatableResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UserHttpRequests extends BaseSpec {
    public ValidatableResponse getUserList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(EndPoints.userEndPoint)
                .then();
    }

    public ValidatableResponse getUserById(UUID id) {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(EndPoints.userEndPoint + id)
                .then();
    }

    public ValidatableResponse createUser(UserInput userInput) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(userInput)
                .when()
                .post(EndPoints.userEndPoint)
                .then();
    }

    public ValidatableResponse editUser(UUID id, UserInput userInput) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(userInput)
                .put(EndPoints.userEndPoint + id)
                .then();
    }

    public void deleteUser(UUID id) {
        given()
                .spec(getBaseSpec())
                .delete(EndPoints.userEndPoint + id)
                .then()
                .statusCode(200);
    }
}

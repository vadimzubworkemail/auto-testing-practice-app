package com.example.autotestingpracticeapp.rest_assured;

import com.example.autotestingpracticeapp.pojo.UserInput;
import io.restassured.response.ValidatableResponse;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class UserRequest extends BaseSpec {
    private final String userEndPoint = "users/";

    public ValidatableResponse getUsersList() {
        return given().spec(getBaseSpec()).when().get(userEndPoint).then();
    }

    public ValidatableResponse getUserId(UUID uuid) {
        return given().spec(getBaseSpec()).when().get(userEndPoint + uuid).then();
    }

    public ValidatableResponse creatUser(UserInput userInput) {
        return given().spec(getBaseSpec()).and().body(userInput).when().post(userEndPoint).then();
    }

    public ValidatableResponse editUser(UUID uuid, UserInput userInput){
        return given().spec(getBaseSpec()).and().body(userInput).when().put(userEndPoint + uuid).then();
    }

    public ValidatableResponse deleteUser(UUID uuid){
        return given().spec(getBaseSpec()).when().delete(userEndPoint + uuid).then();
    }
}

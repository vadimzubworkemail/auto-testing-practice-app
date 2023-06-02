package com.example.autotestingpracticeapp.api_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class ConnectionTest {

    @Test
    public void makeSureTheConnectionIsWorkingOk200() {
        given().when().get("http://localhost:8080/users/").then().statusCode(200);
    }
}

package com.example.autotestingpracticeapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

@SpringBootTest
public class TestConnection {
    @Test
    public void checkConnectionOk200(){
        given().when().get("http://localhost:8080/users").then().statusCode(200);
    }
}

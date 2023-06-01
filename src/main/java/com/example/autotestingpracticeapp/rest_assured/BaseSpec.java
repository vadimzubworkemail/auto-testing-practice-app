package com.example.autotestingpracticeapp.rest_assured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseSpec {
    public RequestSpecification getBaseSpec (){
        String URL = "http://localhost:8080/";
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .setContentType(ContentType.JSON)
                .build();
    }
}


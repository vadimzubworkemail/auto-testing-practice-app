package com.example.autotestingpracticeapp.http_client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseSpec extends BaseHTTPClient {

    private String getBaseHost() {
        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            return "http://localhost";
        } else {
            return RestAssured.baseURI = baseHost;
        }
    }

    private Integer getBasePort() {
        String basePort = System.getProperty("server.port");
        if (basePort == null) {
            return RestAssured.port = 8080;
        } else {
            return RestAssured.port = Integer.parseInt(basePort);
        }
    }

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(getBaseHost())
                .setPort(getBasePort())
                .setConfig(config)
                .setContentType(ContentType.JSON)
                .build();
    }


}

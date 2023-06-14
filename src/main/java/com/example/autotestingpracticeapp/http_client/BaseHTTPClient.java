package com.example.autotestingpracticeapp.http_client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseHTTPClient {

    final String configPath = "src/main/resources/integration-app.properties";

    private Properties getAppProperties() {
        final Properties appProperties = new Properties();
        try {
            appProperties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProperties;
    }

    private final RestAssuredConfig config = RestAssuredConfig.newConfig()
            .sslConfig(new SSLConfig().relaxedHTTPSValidation())
            .redirect(new RedirectConfig().followRedirects(true))
            .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));

    private String getBaseHost() {
        String baseHost = getAppProperties().getProperty("server.host");
        if (baseHost == null) {
            return RestAssured.DEFAULT_URI;
        } else {
            return RestAssured.baseURI = baseHost;
        }
    }

    private Integer getBasePort() {
        String basePort = getAppProperties().getProperty("server.port");
        if (basePort == null) {
            return RestAssured.DEFAULT_PORT;
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

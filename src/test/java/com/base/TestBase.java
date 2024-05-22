package com.base;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeEach
    //This is just a separate method for setup
    public void setup() {
        // Setting the base URI for RestAssured to the JSONPlaceholder API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Create a custom configuration and apply Allure filter
        RestAssuredConfig config = RestAssured.config();
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        RestAssured.config = config;
    }
}
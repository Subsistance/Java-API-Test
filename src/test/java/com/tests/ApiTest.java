package com.tests;

import com.base.TestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTest extends TestBase {

    // Parameterized test for creating posts with different data sets for more JUnit use :)
    @ParameterizedTest(name = "testCreatePost({0}, {1}, {2})")
    @CsvSource({
        "foo, bar, 1", // First set of data
        "baz, qux, 2"  // Second set of data
    })
    public void testCreatePost(String title, String body, int userId) {
        String requestBody = String.format("""
                {
                  "title": "%s",
                  "body": "%s",
                  "userId": %d
                }""", title, body, userId);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("title", equalTo(title))
                .body("body", equalTo(body))
                .body("userId", equalTo(userId));
    }

    // Test for reading a single post
    @Test
    public void testReadSinglePost() {

        int postId = 1;

        given()
        .when()
                .get("/posts/" + postId)
        .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(postId))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("body", equalTo("""
                        quia et suscipit
                        suscipit recusandae consequuntur expedita et cum
                        reprehenderit molestiae ut ut quas totam
                        nostrum rerum est autem sunt rem eveniet architecto"""));
    }

    // Test for reading multiple posts
    @Test
    public void testReadMultiplePosts() {
        given()
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].userId", notNullValue())
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].body", notNullValue());
    }

    // Test for updating a post
    @Test
    public void testUpdatePost() {

        int postId = 1;

        String updatedTitle = "updated title";
        String updatedBody = "updated body";
        int userId = 1;

        String requestBody = String.format("""
                {
                  "id": %d,
                  "title": "%s",
                  "body": "%s",
                  "userId": %d
                }""", postId, updatedTitle, updatedBody, userId);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .put("/posts/" + postId)
        .then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", equalTo(updatedTitle))
                .body("body", equalTo(updatedBody))
                .body("userId", equalTo(userId));
    }

    // Test for deleting a post
    @Test
    public void testDeletePost() {

        int postId = 1;

        given()
        .when()
                .delete("/posts/" + postId)
        .then()
                .statusCode(200);

        // Verifying that the post was deleted is not necessary in this task due to mocks
    }
}

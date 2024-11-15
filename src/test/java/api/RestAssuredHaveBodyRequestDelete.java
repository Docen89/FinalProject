package api;


import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestAssuredHaveBodyRequestDelete {
  static AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  private Response response;

  public RestAssuredHaveBodyRequestDelete delete(
      String endpoint,
      String userName,
      String password,
      Object bodyData) {
    this.response = given()
        .filter(allureFilter)
        .auth()
        .preemptive()
        .basic(userName, password)
        .contentType(ContentType.JSON)
        .body(bodyData)
        .when()
        .log()
        .all()
        .delete(endpoint)
        .then()
        .log()
        .all()
        .extract()
        .response();
    return this;
  }

  public RestAssuredHaveBodyRequestDelete responseStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestAssuredHaveBodyRequestDelete responseJson(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }
}
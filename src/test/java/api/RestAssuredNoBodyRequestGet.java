package api;

import static io.restassured.RestAssured.given;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestAssuredNoBodyRequestGet {

  static AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  private Response response;


  public RestAssuredNoBodyRequestGet get(

      String endpoint) {
    this.response = given()
        .filter(allureFilter)
        .when()
        .log()
        .all()
        .get(endpoint)
        .then()
        .log()
        .all()
        .extract()
        .response();
    return this;
  }

  public RestAssuredNoBodyRequestGet responseStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestAssuredNoBodyRequestGet responseJson(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }

}

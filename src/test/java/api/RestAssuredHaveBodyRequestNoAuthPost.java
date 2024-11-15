package api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import org.hamcrest.Matcher;

@Getter

public class RestAssuredHaveBodyRequestNoAuthPost {

  static AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  private Response response;

  public RestAssuredHaveBodyRequestNoAuthPost post(
      String endpoint,
      Object bodyData
  ) {
    this.response = given()
        .filter(allureFilter)
        .contentType(ContentType.JSON)
        .body(bodyData)
        .when()
        .log()
        .all()
        .post(endpoint)
        .then()
        .log()
        .all()
        .extract()
        .response();
    return this;
  }

  public RestAssuredHaveBodyRequestNoAuthPost responseStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestAssuredHaveBodyRequestNoAuthPost responseJson(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }

}

package api;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestAssuredHaveBodyRequestNoAuthPost {
  private Response response;

  public RestAssuredHaveBodyRequestNoAuthPost post(
      String endpoint,
      Object bodyData
      ) {
    this.response = given()
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

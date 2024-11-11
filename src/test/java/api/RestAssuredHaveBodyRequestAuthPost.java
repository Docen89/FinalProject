package api;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import org.hamcrest.Matcher;

@Getter
public class RestAssuredHaveBodyRequestAuthPost {
  private Response response;

  public RestAssuredHaveBodyRequestAuthPost post(
      String endpoint,
      Object bodyData,
      String userName,
      String password) {
    this.response = given()
        .auth()
        .preemptive()
        .basic(userName, password)
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

  public RestAssuredHaveBodyRequestAuthPost responseStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestAssuredHaveBodyRequestAuthPost responseJson(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }
}

package api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

public class RestAssuredNoBodyRequestDelete {

  private Response response;

  public RestAssuredNoBodyRequestDelete delete(
      String endpoint,
      String userName,
      String password
  ) {
    this.response = given()
        .filter(new AllureRestAssured())
        .auth()
        .preemptive()
        .basic(userName, password)
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

  public RestAssuredNoBodyRequestDelete responseStatusCode(int statusCode) {
    response.then().assertThat().statusCode(statusCode);
    return this;
  }

  public RestAssuredNoBodyRequestDelete responseJson(String jsonPath, Matcher matcher) {
    response.then().assertThat().body(jsonPath, matcher);
    return this;
  }


}

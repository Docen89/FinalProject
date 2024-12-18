package api.check;

import io.restassured.response.Response;
import api.check.Condition;


public class StatusCodeCheck implements Condition {

  private final int statusCode;

  public StatusCodeCheck(int statusCode) {
    this.statusCode = statusCode;
  }

  public void check(Response response) {
    response.then().assertThat().statusCode(statusCode);
  }

  public String toString() {
    return String.format("HTTP status code - %s", statusCode);
  }

}

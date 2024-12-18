package ru.demoqa.api.check;

import io.restassured.response.Response;
import org.hamcrest.Matcher;
import ru.demoqa.api.Condition;


public class BodyFieldCheck implements Condition {

  private final String jsonPath;
  private final Matcher matcher;

  public BodyFieldCheck(String jsonPath, Matcher matcher) {
    this.jsonPath = jsonPath;
    this.matcher = matcher;
  }

  public void check(Response response) {
    response.then().assertThat().body(jsonPath, matcher);
  }

  public String toString() {
    return String.format("В теле ответа поле '%s' - %s", jsonPath, matcher);
  }

}
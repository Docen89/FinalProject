package ru.demoqa.api.check;

import org.hamcrest.Matcher;


public class VerificationProcedures {

  public static StatusCodeCheck statusCode(int code) {
    return new StatusCodeCheck(code);
  }

  public static BodyFieldCheck bodyField(String jsonPath, Matcher matcher) {
    return new BodyFieldCheck(jsonPath, matcher);
  }

}
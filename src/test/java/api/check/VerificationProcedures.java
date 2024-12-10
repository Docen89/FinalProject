package api.check;

import api.check.StatusCodeCheck;
import org.hamcrest.Matcher;
import api.check.BodyFieldCheck;


public class VerificationProcedures {

  public static StatusCodeCheck statusCode(int code) {
    return new StatusCodeCheck(code);
  }

  public static BodyFieldCheck bodyField(String jsonPath, Matcher matcher) {
    return new BodyFieldCheck(jsonPath, matcher);
  }

}
package api.check;

import io.restassured.response.Response;

public interface Condition {

  void check(Response response);

}

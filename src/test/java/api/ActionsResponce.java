package api;

import io.restassured.response.Response;

public class ActionsResponce {
  private final Response response;

  public ActionsResponce(Response response) {
    this.response = response;
  }

  public String getBodyFieldString(String jsonPath) {
    String bodyField = response.jsonPath().getString(jsonPath);
    return bodyField;}

  public int getStatusCode() {
      return response.getStatusCode();

  }

  public String getCookie(String cookieName) {
      return response.getCookie(cookieName);

    }

  public <T> T asPojo(Class<T> tClass) {
    return response.as(tClass);
  }

}

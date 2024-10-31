package Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CreateUser {
 public Map<String,String>createNewUser() {
  Response response = RestAssured.given()
      .contentType(ContentType.JSON)
      .body(new File("src/test/resources/logopass.json"))
      .log()
      .all()
      .when()
      .post("https://demoqa.com/Account/v1/User")
      .then()
      .log()
      .all()
      .extract()
      .response();
  Map<String, String> userIdAndUsernameAndBooks = new HashMap<>();
  userIdAndUsernameAndBooks.put("valueUserId", response.path("userID"));
  userIdAndUsernameAndBooks.put("valueUserName", response.path("username"));
  userIdAndUsernameAndBooks.put("valueBooks", response.path("books"));
  return userIdAndUsernameAndBooks;
 }
}

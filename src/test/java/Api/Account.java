package Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import model.RequestLoginBodyModel;

public class Account {
 public Map<String,String>createNewUser(String newUserNameValue,String newUserPassword,String userPath) {
  RequestLoginBodyModel requestBookModel = new RequestLoginBodyModel();
  requestBookModel.setUserName(newUserNameValue);
  requestBookModel.setPassword(newUserPassword);
  Response response = RestAssured.given()
      .contentType(ContentType.JSON)
      .body(requestBookModel)
      .log()
      .all()
      .when()
      .post(userPath)
      .then()
      .log()
      .all()
      .extract()
      .response();
  Map<String, String>userIdAndUserNameAndBooksAndStatus = new HashMap<>();
  userIdAndUserNameAndBooksAndStatus.put("userIdValue", response.path("userID"));
  userIdAndUserNameAndBooksAndStatus.put("userNameValue", response.path("username"));
   return userIdAndUserNameAndBooksAndStatus;
 }

 public Map<String,String>authorized(String userNameValue, String passwordValue, String loginPath){
  RequestLoginBodyModel requestBookModel = new RequestLoginBodyModel();
  requestBookModel.setUserName(userNameValue);
  requestBookModel.setPassword(passwordValue);
  Response response = RestAssured.given()
      .contentType(ContentType.JSON)
      .body(requestBookModel)
      .log()
      .all()
      .when()
      .post(loginPath)
      .then()
      .log()
      .all()
      .extract()
      .response();
  Map<String, String>tokenAndExpiresAndUserIdAndStatusValue = new HashMap<>();
  tokenAndExpiresAndUserIdAndStatusValue.put("token", response.path("token"));
  tokenAndExpiresAndUserIdAndStatusValue.put("expires", response.path("expires"));
  tokenAndExpiresAndUserIdAndStatusValue.put("userId",response.path("userId"));
  tokenAndExpiresAndUserIdAndStatusValue.put("status", String.valueOf(response.getStatusCode()));
  return tokenAndExpiresAndUserIdAndStatusValue;
 }

 public Map<String, String> deleteUser(String userName, String password, String bookPath) {

  Response response = RestAssured.given()
      .auth()
      .preemptive()
      .basic(userName, password)
      .log()
      .all()
      .delete(bookPath)
      .then()
      .log()
      .all()
      .extract()
      .response();

  Map<String, String> responseDeleteUser = new HashMap<>();
  responseDeleteUser.put("StatusValue", String.valueOf(response.getStatusCode()));
  return responseDeleteUser;
 }


}

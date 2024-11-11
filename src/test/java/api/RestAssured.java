package api;

import static io.restassured.RestAssured.given;


import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class RestAssured {


  public Response addBook(
      String baseUri,
      String userName,
      String password,
      String bookStorePath,
      String allBookPath,
      Object bodyData){
    return given()
        .auth()
        .preemptive()
        .basic(userName, password)
        .contentType(ContentType.JSON)
        .body(bodyData)
        .log()
        .all()
        .post(baseUri + bookStorePath + allBookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }


  public Response newUser(
      String baseUri,
      String userPath,
      Object bodyDate
      ) {
    return given().
        contentType(ContentType.JSON)
        .body(bodyDate)
        .log()
        .all()
        .when()
        .post(baseUri + userPath)
        .then()
        .log()
        .all()
        .extract()
        .response();

  }

  public Response authorization(
      String baseUri,
      String loginPath,
      Object bodyDate
      ){
    return given()
        .contentType(ContentType.JSON)
        .body(bodyDate)
        .log()
        .all()
        .when()
        .post(baseUri + loginPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public Response deleteUser(
      String baseUri,
      String newUserNameValue,
      String newUserPassword,
      String userPath){
    return given()
        .auth()
        .preemptive()
        .basic(newUserNameValue, newUserPassword)
        .log()
        .all()
        .delete(baseUri + userPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }



}

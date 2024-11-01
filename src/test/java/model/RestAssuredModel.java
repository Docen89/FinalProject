package model;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Collections;

public class RestAssuredModel {

  public Response newUser(String newUserNameValue, String newUserPassword, String userPath) {
    RequestLoginBodyModel requestBookModel = new RequestLoginBodyModel();
    requestBookModel.setUserName(newUserNameValue);
    requestBookModel.setPassword(newUserPassword);
    return given().
        contentType(ContentType.JSON)
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

  }

  public Response authorization(String userNameValue, String passwordValue, String loginPath) {
    RequestLoginBodyModel loginBodyModel = new RequestLoginBodyModel();
    loginBodyModel.setUserName(userNameValue);
    loginBodyModel.setPassword(passwordValue);
    return given()
        .contentType(ContentType.JSON)
        .body(loginBodyModel)
        .log()
        .all()
        .when()
        .post(loginPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public Response deleteUser(String userName, String password, String bookPath) {
    return given()
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
  }

  public Response deleteBook(String userName, String password, String bookPath,
      String isbnValue, String userId){
    DeleteBooKBodyResponse deleteBooKBodyResponse = new DeleteBooKBodyResponse();
    deleteBooKBodyResponse.setUserId(userId);
    deleteBooKBodyResponse.setIsbn(isbnValue);
    return given()
        .auth()
        .preemptive()
        .basic(userName, password)
        .contentType(ContentType.JSON)
        .body(deleteBooKBodyResponse)
        .log()
        .all()
        .delete(bookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public  Response viewBookOther(String bookPath){
    return given()
        .log()
        .all()
        .get(bookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public  Response addBook(String userName, String password, String bookPath,
      String isbnValue, String userId){
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(isbnValue);
    requestBookModel.setUserId(userId);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    return given()
        .auth()
        .preemptive()
        .basic(userName, password)
        .contentType(ContentType.JSON)
        .body(requestBookModel)
        .log()
        .all()
        .post(bookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();

  }

}

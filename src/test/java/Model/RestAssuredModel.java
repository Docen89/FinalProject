package Model;

import static io.restassured.RestAssured.given;

import Test.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Collections;

public class RestAssuredModel extends BaseTest {

  public Response newUser(String baseUri,String newUserNameValue, String newUserPassword, String userPath) {
    RequestLoginBodyModel requestBookModel = new RequestLoginBodyModel();
    requestBookModel.setUserName(newUserNameValue);
    requestBookModel.setPassword(newUserPassword);
    return given().
        contentType(ContentType.JSON)
        .body(requestBookModel)
        .log()
        .all()
        .when()
        .post(baseUri+userPath)
        .then()
        .log()
        .all()
        .extract()
        .response();

  }

  public Response authorization(String baseUri,String userNameValue, String passwordValue, String loginPath) {
    RequestLoginBodyModel loginBodyModel = new RequestLoginBodyModel();
    loginBodyModel.setUserName(userNameValue);
    loginBodyModel.setPassword(passwordValue);
    return given()
        .contentType(ContentType.JSON)
        .body(loginBodyModel)
        .log()
        .all()
        .when()
        .post(baseUri+loginPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public Response deleteUser(String baseUri,String newUserNameValue, String newUserPassword, String userPath) {
    return given()
        .auth()
        .preemptive()
        .basic(newUserNameValue, newUserPassword)
        .log()
        .all()
        .delete(baseUri+userPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public Response deleteBook(String baseUri,String userName, String password, String bookStorePath, String userId,
      String realIsbnValue,String boolPath){
    DeleteBooKBodyResponse deleteBooKBodyResponse = new DeleteBooKBodyResponse();
    deleteBooKBodyResponse.setUserId(userId);
    deleteBooKBodyResponse.setIsbn(realIsbnValue);
    return given()
        .auth()
        .preemptive()
        .basic(userName, password)
        .contentType(ContentType.JSON)
        .body(deleteBooKBodyResponse)
        .log()
        .all()
        .delete(baseUri+bookStorePath+boolPath)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public  Response viewBookOther(String baseUri,String bookStorePath, String delViewBookPath,String realIsbnValue){
    return given()
        .log()
        .all()
        .get(baseUri+bookStorePath+delViewBookPath+realIsbnValue)
        .then()
        .log()
        .all()
        .extract()
        .response();
  }

  public  Response addBook(String baseUri,String userName, String password, String bookStorePath,String allBookPath,String realIsbnValue, String userId){
    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(realIsbnValue);
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
        .post(baseUri+bookStorePath+allBookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();

  }

}

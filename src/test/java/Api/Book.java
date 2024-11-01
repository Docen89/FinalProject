package Api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.DeleteBooKBodyResponse;
import model.IsbnPartialModel;
import model.RequestBookModel;

public class Book {

  public Map<String, String> addBookOldUser(String userName, String password, String bookPath,
      String isbnValue, String userId) {

    RequestBookModel requestBookModel = new RequestBookModel();
    IsbnPartialModel isbnPartialModel = new IsbnPartialModel();
    isbnPartialModel.setIsbn(isbnValue);
    requestBookModel.setUserId(userId);
    requestBookModel.setCollectionOfIsbns(Collections.singletonList(isbnPartialModel));
    Response response = RestAssured.given()
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

    Map<String, String> responseAddBookOldUser = new HashMap<>();
    responseAddBookOldUser.put("userIdValue", response.path("userId"));
    responseAddBookOldUser.put("usernameValue", response.path("username"));
    responseAddBookOldUser.put("passwordValue", response.path("password"));
    responseAddBookOldUser.put("tokenValue", response.path("token"));
    responseAddBookOldUser.put("expiresValue", response.path("expires"));
    responseAddBookOldUser.put("created_dateValue", response.path("created_date"));
    responseAddBookOldUser.put("statusValue", String.valueOf(response.getStatusCode()));
    responseAddBookOldUser.put("messageValue", response.path("message"));
    responseAddBookOldUser.put("codeValue",response.path("code"));
    return responseAddBookOldUser;
  }


  public Map<String, String> deleteBook(String userName, String password, String bookPath,
      String isbnValue, String userId) {

    DeleteBooKBodyResponse deleteBooKBodyResponse = new DeleteBooKBodyResponse();
    deleteBooKBodyResponse.setUserId(userId);
    deleteBooKBodyResponse.setIsbn(isbnValue);
    Response response = RestAssured.given()
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

    Map<String, String> responseDeleteBook = new HashMap<>();
    responseDeleteBook.put("StatusValue", String.valueOf(response.getStatusCode()));
    return responseDeleteBook;
  }

  public Map<String, String> viewBook(String bookPath) {
    Response response = RestAssured.given()
        .log()
        .all()
        .get(bookPath)
        .then()
        .log()
        .all()
        .extract()
        .response();

    Map<String, String> responseViewBook = new HashMap<>();
    responseViewBook.put("isbnValue",response.path("isbn"));
    responseViewBook.put("titleValue",response.path("title"));
    responseViewBook.put("authorValue",response.path("author"));
    responseViewBook.put("messageValue",response.path("message"));
    return responseViewBook;
  }

}

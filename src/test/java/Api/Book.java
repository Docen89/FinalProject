package Api;

import Test.BaseTest;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import Model.RestAssuredModel;


public class Book extends BaseTest {

  RestAssuredModel restAssuredModel = new RestAssuredModel();

  public Map<String, String> addBookOldUser() {
    Response response = restAssuredModel.addBook(cfg.baseUri(), cfg.userNameValue(),
        cfg.passwordValue(),
        cfg.bookStorePath(), cfg.allBookPath(), cfg.realIsbnValue(), cfg.userId());
    Map<String, String> responseAddBookOldUser = new HashMap<>();
    responseAddBookOldUser.put("userIdValue", response.path("userId"));
    responseAddBookOldUser.put("usernameValue", response.path("username"));
    responseAddBookOldUser.put("passwordValue", response.path("password"));
    responseAddBookOldUser.put("tokenValue", response.path("token"));
    responseAddBookOldUser.put("expiresValue", response.path("expires"));
    responseAddBookOldUser.put("created_dateValue", response.path("created_date"));
    responseAddBookOldUser.put("statusValue", String.valueOf(response.getStatusCode()));
    responseAddBookOldUser.put("messageValue", response.path("message"));
    responseAddBookOldUser.put("codeValue", response.path("code"));
    return responseAddBookOldUser;
  }


  public Map<String, String> deleteBookUser() {
    Response response = restAssuredModel.deleteBook(cfg.baseUri(), cfg.userNameValue(),
        cfg.passwordValue(), cfg.bookStorePath(), cfg.userId(), cfg.realIsbnValue(),
        cfg.bookPath());
    Map<String, String> responseDeleteBook = new HashMap<>();
    responseDeleteBook.put("StatusValue", String.valueOf(response.getStatusCode()));
    return responseDeleteBook;
  }

  public Map<String, String> viewBook() {
    Response response = restAssuredModel.viewBookOther(cfg.baseUri(), cfg.bookStorePath(),
        cfg.delViewBookPath(), cfg.realIsbnValue());
    Map<String, String> responseViewBook = new HashMap<>();
    responseViewBook.put("isbnValue", response.path("isbn"));
    responseViewBook.put("titleValue", response.path("title"));
    responseViewBook.put("authorValue", response.path("author"));
    responseViewBook.put("messageValue", response.path("message"));
    return responseViewBook;
  }

  public Map<String, String> viewNotRealBook() {
    Response response = restAssuredModel.viewBookOther(cfg.baseUri(), cfg.bookStorePath(),
        cfg.delViewBookPath(),
        cfg.notRealIsbnValue());
    Map<String, String> responseViewBook = new HashMap<>();
    responseViewBook.put("isbnValue", response.path("isbn"));
    responseViewBook.put("titleValue", response.path("title"));
    responseViewBook.put("authorValue", response.path("author"));
    responseViewBook.put("messageValue", response.path("message"));
    return responseViewBook;
  }

}

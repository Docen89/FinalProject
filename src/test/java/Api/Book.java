package Api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import model.RestAssuredModel;

public class Book {

  RestAssuredModel restAssuredModel = new RestAssuredModel();

  public Map<String, String> addBookOldUser() {
    Response response = restAssuredModel.addBook("Docen89",
        "Docen1313!", "https://demoqa.com/BookStore/v1/Books", "9781449337711",
        "03ca9059-3f99-4f3a-9cd1-57722d0c9ae0");
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
    Response response = restAssuredModel.deleteBook("Docen89", "Docen1313!",
        "https://demoqa.com/BookStore/v1/Book", "9781449337711",
        "03ca9059-3f99-4f3a-9cd1-57722d0c9ae0");
    Map<String, String> responseDeleteBook = new HashMap<>();
    responseDeleteBook.put("StatusValue", String.valueOf(response.getStatusCode()));
    return responseDeleteBook;
  }

  public Map<String, String> viewBook() {
    Response response = restAssuredModel.viewBookOther(
        "https://demoqa.com/BookStore/v1/Book?ISBN=" + "9781449337711");
    Map<String, String> responseViewBook = new HashMap<>();
    responseViewBook.put("isbnValue", response.path("isbn"));
    responseViewBook.put("titleValue", response.path("title"));
    responseViewBook.put("authorValue", response.path("author"));
    responseViewBook.put("messageValue", response.path("message"));
    return responseViewBook;
  }

  public Map<String, String> viewNotRealBook() {
    Response response = restAssuredModel.viewBookOther(
        "https://demoqa.com/BookStore/v1/Book?ISBN=" + "978144933771123223232");
    Map<String, String> responseViewBook = new HashMap<>();
    responseViewBook.put("isbnValue", response.path("isbn"));
    responseViewBook.put("titleValue", response.path("title"));
    responseViewBook.put("authorValue", response.path("author"));
    responseViewBook.put("messageValue", response.path("message"));
    return responseViewBook;
  }

}

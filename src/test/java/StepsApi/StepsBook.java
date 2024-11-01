package StepsApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Api.Book;
import io.qameta.allure.Step;
import java.util.Map;
import lombok.Getter;

@Getter
public class StepsBook {

  Book book = new Book();
  String isbnBook;
  String responseMessageAddBook;
  String responseCodeAddBook;
  String responseStatusDeleteBook;
  String realTitleBook;
  String randomNoRealTitleBookMessage;


  @Step("Добавляем книгу пользователю в профиль")
  public void addBookProfileUser() {
    Map<String, String> tokenAndExpiresAndUserIdAndStatusValue = book.addBookOldUser();
    responseCodeAddBook = tokenAndExpiresAndUserIdAndStatusValue.get("statusValue");
    responseMessageAddBook = tokenAndExpiresAndUserIdAndStatusValue.get("messageValue");
  }

  @Step("Проверяем статус ответа на запрос добавления книги в профиль")
  public void ckeckStatusResponseAddBooKProfileUser() {
    assertEquals("201", responseCodeAddBook);
  }

  @Step("Проверяем сообщение в ответе на запрос добавления книги в профиль")
  public void ckeckMessageResponseAddBooKProfileUser() {
    assertEquals("ISBN already present in the User's Collection!", responseMessageAddBook);
  }

  @Step("Удаляем книгу у пользователя из профиля")
  public void deleteBookProfileUser() {
    Map<String, String> responseDeleteBook = book.deleteBookUser();
    responseStatusDeleteBook = responseDeleteBook.get("StatusValue");
  }

  @Step("Проверяем статус ответа  на запрос удаления книги из профиля")
  public void checkResponseStatusDeleteBookProfile() {
    assertEquals("204", responseStatusDeleteBook);
  }

  @Step("Запрашиваем существующую книгу")
  public void getRealBook() {
    Map<String, String> responseViewBook = book.viewBook();
    realTitleBook = responseViewBook.get("titleValue");
  }

  @Step("Проверяем название книги, которую мы запросили")
  public void ckeckTitleRealBook() {
    assertEquals("Designing Evolvable Web APIs with ASP.NET", realTitleBook);
  }

  @Step("Запрашиваем несуществующую книгу")
  public void getNoRealBook() {
    Map<String, String> responseViewBook = book.viewNotRealBook();
    randomNoRealTitleBookMessage = responseViewBook.get("messageValue");
  }

  @Step("Проверяем сообщение в ответе на запрос несуществующей книги")
  public void checkMessageResponseNotRealBook() {
    assertEquals("ISBN supplied is not available in Books Collection!",
        randomNoRealTitleBookMessage);
  }


}

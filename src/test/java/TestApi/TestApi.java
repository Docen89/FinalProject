package TestApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import StepsApi.StepsAccount;
import StepsApi.StepsBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestApi {
  StepsAccount stAp = new StepsAccount();
  StepsBook stBk = new StepsBook();

  @Test
  @DisplayName("Создание нового пользователя")
 public void createNewUser(){
    stAp.createNewAccount();
    assertEquals("Docen90",stAp.getCheckNameValueResponce());
    stAp.deleteNewUser();
  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser(){
    stAp.authorization();
    assertEquals("200",stAp.getCheckStatusValueResponseLogin());

  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser(){
    stBk.addBookProfileUser();
    assertEquals("1210",stBk.getResponseCodeAddBook());
    stBk.deleteBookProfileUser();
  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser(){
    stBk.addBookProfileUser();
    stBk.addBookProfileUser();
    assertEquals("ISBN already present in the User's Collection!",stBk.getResponseMessageAddBook());
    assertEquals("1210",stBk.getResponseCodeAddBook());
  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser(){
    stBk.addBookProfileUser();
    stBk.deleteBookProfileUser();
    assertEquals("204",stBk.getResponseStatusDeleteBook());
  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser(){
    stAp.createNewAccount();
    stAp.deleteNewUser();
    assertEquals("204",stAp.getCheckStatusDeleteUser());
  }

  @Test
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore(){
    stBk.getRealBook();
    assertEquals("Designing Evolvable Web APIs with ASP.NET",stBk.getRandomTitleBook());
  }

  @Test
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore(){
    stBk.getNoRealBook();
    assertEquals("ISBN supplied is not available in Books Collection!",stBk.getRandomNoRealTitleBookMessage());
  }

}

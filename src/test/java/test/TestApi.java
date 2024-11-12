package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import steps.api.StepsAccount;
import steps.api.StepsBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestApi extends test.BaseTest {

  StepsAccount stepsAccount = new StepsAccount();
  StepsBook stepsBook = new StepsBook();


  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser() throws JsonProcessingException {
    stepsAccount.createNewAccount();
    stepsAccount.deleteNewUser();

  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser() {
    stepsAccount.authorization();

  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() throws JsonProcessingException {
    stepsBook.addBookProfileUser();
    stepsBook.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser() throws JsonProcessingException {
    stepsBook.addBookProfileUser();
    stepsBook.checkMessageResponseRepeatedAddBooKProfileUser();
    stepsBook.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() throws JsonProcessingException {
    stepsBook.addBookProfileUser();
    stepsBook.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() throws JsonProcessingException {
    stepsAccount.createNewAccount();
    stepsAccount.deleteNewUser();

  }

  @Test
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    stepsBook.getRealBook();
  }

  @Test
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    stepsBook.getNoRealBook();

  }

}

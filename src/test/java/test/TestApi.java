package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.api.StepsApi;


public class TestApi extends test.BaseTest {

 StepsApi stepsApi = new StepsApi();


  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser() throws JsonProcessingException {
    stepsApi.createNewAccount();
    stepsApi.deleteNewUser();

  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser() throws JsonProcessingException {
    stepsApi.authorization() ;

  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() throws JsonProcessingException {
    stepsApi.authorization();
    stepsApi.addBookProfileUser();
    stepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser() throws JsonProcessingException {
    stepsApi.authorization();
    stepsApi.addBookProfileUser();
    stepsApi.checkMessageResponseRepeatedAddBooKProfileUser();
    stepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser()throws JsonProcessingException {
    stepsApi.authorization();
    stepsApi.addBookProfileUser();
    stepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() throws JsonProcessingException {
    stepsApi.createNewAccount();
    stepsApi.deleteNewUser();

  }

  @Test
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    stepsApi.getRealBook();
  }

  @Test
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    stepsApi.getNoRealBook();

  }

}

package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import steps.api.StepsAccount;
import steps.api.StepsBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestApi extends test.BaseTest {

  static AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  StepsAccount stepsAccount = new StepsAccount();
  StepsBook stepsBook = new StepsBook();

  @BeforeEach
  public void start(){
    RestAssured.baseURI = "https://demoqa.com";
  }

  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser ()  throws JsonProcessingException{
    stepsAccount.createNewAccount ();
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
  public void bookIsAlreadyThereUser() throws JsonProcessingException{
    stepsBook.addBookProfileUser();
    stepsBook.checkMessageResponseRepeatedAddBooKProfileUser();
    stepsBook.deleteBookProfileUser();
  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser()throws JsonProcessingException {
    stepsBook.addBookProfileUser() ;
    stepsBook.deleteBookProfileUser();
  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser()  throws JsonProcessingException{
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

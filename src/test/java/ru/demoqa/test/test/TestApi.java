package test;


import static api.check.VerificationProcedures.bodyField;
import static api.check.VerificationProcedures.statusCode;

import static org.hamcrest.Matchers.equalTo;
import static ru.demoqa.test.test.BaseTest.cfg;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.api.StepsApi;

@Owner("T. Popov")
@Epic("API")

public class TestApi {

  StepsApi stepsApi = new StepsApi();

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    stepsApi.createNewAccount().shouldHave(statusCode(201));
    stepsApi.clearDeleteNewUser();
  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authUser() {
    stepsApi.getToken(cfg.oldPasswordValue(), cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("result", equalTo("User authorized successfully.")));
    stepsApi.authorization(cfg.oldPasswordValue(), cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("username", equalTo(cfg.oldUserNameValue())));
  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация незарегистрированным пользователем")
  public void authNotRegisterUser() {
    stepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue())
        .shouldHave(statusCode(200)).shouldHave(bodyField("status", equalTo("Failed")))
        .shouldHave(bodyField("result", equalTo("User authorization failed.")));
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() {
    stepsApi.getUserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsApi.addBookProfileUser().shouldHave(statusCode(201));
    stepsApi.deleteBookProfileUser().shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser() {
    stepsApi.testDataAddBookToProfileUser();
    stepsApi.checkMessageResponseRepeatedAddBooKProfileUser().shouldHave(statusCode(400))
        .shouldHave(
            bodyField("message", equalTo("ISBN already present in the User's Collection!")));
    stepsApi.clearDeleteBookFromUserProfile();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    stepsApi.getUserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsApi.addBookProfileUser();
    stepsApi.deleteBookProfileUser().shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() {
    stepsApi.testDataAddNewUserAndUserId();
    stepsApi.deleteUser().shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    stepsApi.getBook(steps.api.StepsApi.getIsbnValue()).shouldHave(statusCode(200))
        .shouldHave(bodyField("isbn", equalTo(steps.api.StepsApi.getIsbnValue())));
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    stepsApi.getBook("1223234343").shouldHave(statusCode(400)).shouldHave(
        bodyField("message", equalTo("ISBN supplied is not available in Books Collection!")));
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Проверка сохранения книги в профиле у пользователя")
  public void checkBookProfileUser() {
    stepsApi.testDataAddBookToProfileUser();
    stepsApi.getInfoUser(cfg.oldUserNameValue(), cfg.oldPasswordValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("books[0].isbn", equalTo(steps.api.StepsApi.getIsbnValue())));
    stepsApi.deleteBookProfileUser();
  }

}

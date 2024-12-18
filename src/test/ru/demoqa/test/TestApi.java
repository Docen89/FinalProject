package ru.demoqa.test;



import static org.hamcrest.Matchers.equalTo;
import static ru.demoqa.api.check.VerificationProcedures.bodyField;
import static ru.demoqa.api.check.VerificationProcedures.statusCode;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.demoqa.helpers.IsbnBook;
import ru.demoqa.steps.api.StepsApi;


@Owner("T. Popov")
@Epic("API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestApi {
  StepsApi stepsApi = new StepsApi();

  @Test
  @Feature("Создание пользователя")
  @Order(1)
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    stepsApi.createNewAccount(BaseTest.cfg.newPasswordValue(), BaseTest.cfg.newUserNameValue(),
        BaseTest.cfg.newUserNameValue(), BaseTest.cfg.newPasswordValue()).shouldHave(statusCode(201));
    stepsApi.authorization(BaseTest.cfg.newUserNameValue(), BaseTest.cfg.newPasswordValue());
    stepsApi.clearDeleteNewUser();
  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authUser() {
    stepsApi.getToken(BaseTest.cfg.oldPasswordValue(), BaseTest.cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("result", equalTo("User authorized successfully.")));
    stepsApi.authorization(BaseTest.cfg.oldPasswordValue(), BaseTest.cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("username", equalTo(BaseTest.cfg.oldUserNameValue())));
  }

  @Test
  @Feature("Авторизация")
  @Order(2)
  @DisplayName("Авторизация незарегистрированным пользователем")
  public void authNotRegisterUser() {
    stepsApi.getToken(BaseTest.cfg.newPasswordValue(), BaseTest.cfg.newUserNameValue())
        .shouldHave(statusCode(200)).shouldHave(bodyField("status", equalTo("Failed")))
        .shouldHave(bodyField("result", equalTo("User authorization failed.")));
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() {
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
    stepsApi.addBookProfileUser();
    stepsApi.deleteBookProfileUser().shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() {
    stepsApi.testDataAddNewUserAndUserId();
    stepsApi.deleteUser(stepsApi.getUserId()).shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    stepsApi.getBook(IsbnBook.getInstance().isbnBookValue());
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
    stepsApi.getInfoUser(BaseTest.cfg.oldUserNameValue(), BaseTest.cfg.oldPasswordValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("books[0].isbn", equalTo(IsbnBook.getInstance().isbnBookValue())));
    stepsApi.deleteBookProfileUser();
  }

}

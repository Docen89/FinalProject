package ru.demoqa.test;


import static org.hamcrest.Matchers.equalTo;
import static ru.demoqa.api.check.VerificationProcedures.bodyField;
import static ru.demoqa.api.check.VerificationProcedures.statusCode;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.demoqa.helpers.InfoApiUser;
import ru.demoqa.helpers.IsbnBook;
import ru.demoqa.helpers.KillUserApi;
import ru.demoqa.steps.api.StepsApi;

import static ru.demoqa.test.BaseTest.cfg;


@Owner("T. Popov")
@Epic("API")
public class TestApi {

  StepsApi stepsApi = new StepsApi();

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    stepsApi.testDataAddNewUserAndUserId();
    stepsApi.authUser(cfg.apiKillUserNameValue(),cfg.apiKillPasswordValue());
    stepsApi.clearDeleteKillUser(stepsApi.getUserIdDellUser());
  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authValidUser() {
    stepsApi.getToken(cfg.oldUserNameValue(), cfg.oldPasswordValue()).shouldHave(statusCode(200))
        .shouldHave(bodyField("result", equalTo("User authorized successfully.")));
    stepsApi.userLoginInfo(cfg.oldUserNameValue(), cfg.oldPasswordValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("username", equalTo(cfg.oldUserNameValue())));
  }

  @Test
  @Feature("Авторизация")
  @Order(2)
  @DisplayName("Авторизация незарегистрированным пользователем")
  public void authNotRegisterUser() {
    stepsApi.getToken(cfg.killPasswordValue(), cfg.killUserNameValue()).shouldHave(statusCode(200))
        .shouldHave(bodyField("status", equalTo("Failed")))
        .shouldHave(bodyField("result", equalTo("User authorization failed.")));
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() {
    stepsApi.addBookProfileUser(
        cfg.oldUserNameValue(), cfg.oldPasswordValue(), InfoApiUser.getInstance().getUserIdValueApiUser()).shouldHave(statusCode(201));
    stepsApi.deleteBookProfileUser(InfoApiUser.getInstance().getUserIdValueApiUser()).shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser() {
    stepsApi.testDataAddBookToProfileUser();
    stepsApi.checkMessageResponseRepeatedAddBooKProfileUser(
            InfoApiUser.getInstance().getUserIdValueApiUser())
        .shouldHave(statusCode(400)).shouldHave(
            bodyField("message", equalTo("ISBN already present in the User's Collection!")));
    stepsApi.clearDeleteBookFromUserProfile();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    stepsApi.addBookProfileUser(cfg.oldUserNameValue(), cfg.oldPasswordValue(), InfoApiUser.getInstance().getUserIdValueApiUser());
    stepsApi.deleteBookProfileUser(InfoApiUser.getInstance().getUserIdValueApiUser()).shouldHave(statusCode(204));
  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() {
    stepsApi.testDataAddNewUserAndUserId();
    stepsApi.deleteUser(KillUserApi.getInstance().getUserIdValueApiKillUser())
        .shouldHave(statusCode(204));
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
    stepsApi.getInfoUserProfile(cfg.oldUserNameValue(), cfg.oldPasswordValue(), InfoApiUser.getInstance().getUserIdValueApiUser())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("books[0].isbn", equalTo(IsbnBook.getInstance().isbnBookValue())));
    stepsApi.deleteBookProfileUser(InfoApiUser.getInstance().getUserIdValueApiUser());
  }

}

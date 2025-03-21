package ru.demoqa.steps.api;

import static ru.demoqa.api.Specifications.responseSpec;
import static ru.demoqa.api.Specifications.restRequestSpec;
import static ru.demoqa.api.Specifications.restRequestSpecAuth;
import static ru.demoqa.api.check.VerificationProcedures.statusCode;
import static ru.demoqa.configs.EndPoints.ACCOUNT_GENERATE_TOKEN;
import static ru.demoqa.configs.EndPoints.ACCOUNT_LOGIN;
import static ru.demoqa.configs.EndPoints.ACCOUNT_USER;
import static ru.demoqa.configs.EndPoints.ACCOUNT_USER_USER_ID;
import static ru.demoqa.configs.EndPoints.AUTHORIZED;
import static ru.demoqa.configs.EndPoints.BOOKSTORE_BOOK;
import static ru.demoqa.configs.EndPoints.BOOKSTORE_BOOKS;
import static ru.demoqa.configs.EndPoints.BOOKSTORE_ISBN;
import static io.restassured.RestAssured.given;
import static ru.demoqa.test.BaseTest.cfg;

import lombok.Getter;
import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.helpers.InfoApiUser;
import ru.demoqa.helpers.IsbnBook;
import ru.demoqa.templates.LoginBody.LoginBody;
import ru.demoqa.templates.authorizedBody.RequestAuthorizedBody;
import ru.demoqa.templates.createUserBody.CreateUserBodyRequest;
import ru.demoqa.templates.deleteBookUserBody.DeleteBookUserBody;
import ru.demoqa.templates.addBookUserBody.AddBookUserBody;
import io.qameta.allure.Step;


public class StepsApi {

  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookUserBody addBookUserBody = new AddBookUserBody();
  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();
  LoginBody loginBody = new LoginBody();

  @Getter
  String userIdDellUser;

  @Step("Очистка тестовых данных. Удаление нового пользователя")
  public void clearDeleteKillUser(String userId) {
    deleteUser(userId).shouldHave(statusCode(204));
  }

  @Step("Подготовка тестовых данных. Добавление книги в профиль пользователю")
  public void testDataAddBookToProfileUser() {
    addBookProfileUser(
        cfg.oldUserNameValue(),
        cfg.oldPasswordValue(),
        InfoApiUser.getInstance().getUserIdValueApiUser());
  }

  @Step("Подготовка тестовых данных.Создание нового пользователя")
  public void testDataAddNewUserAndUserId() {
    userIdDellUser = createNewAccount(
        cfg.apiKillUserNameValue(),
        cfg.apiKillPasswordValue(),
        cfg.apiKillUserNameValue(),
        cfg.apiKillPasswordValue())
        .shouldHave(statusCode(201))
        .getBodyFieldString("userID");
  }

  @Step("Создать нового пользователя")
  public ActionsResponce createNewAccount(String userName, String password, String userNameBody,
      String passwordBody) {
    return new ActionsResponce(given()
        .spec(restRequestSpecAuth(userName, password))
        .body(createUserBodyRequest.RequestCreateUserBody(passwordBody, userNameBody))
        .expect()
        .spec(responseSpec())
        .when()
        .post(ACCOUNT_USER));
  }

  @Step("Получить cписок книг")
  public ActionsResponce getListBook() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect()
            .spec(responseSpec())
            .when()
            .get(BOOKSTORE_BOOKS));
  }

  @Step("Получить токен авторизации")
  public ActionsResponce getToken(String userName, String password) {
    return new ActionsResponce(
        given()
        .spec(restRequestSpec())
        .body(requestAuthorizedBody.RequestAuthorizedBody(userName, password))
            .expect()
        .spec(responseSpec())
            .when()
            .post(ACCOUNT_GENERATE_TOKEN));
  }


  @Step("Получить название книги")
  public ActionsResponce getNameBook() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect()
            .spec(responseSpec())
            .when()
            .get(BOOKSTORE_BOOKS));
  }

  @Step("Удалить ранее созданного пользователя")
  public ActionsResponce deleteUser(String userIdValue) {
    return new ActionsResponce(given().spec(restRequestSpec().auth().preemptive()
            .basic(
                cfg.apiKillUserNameValue(),
                cfg.apiKillPasswordValue()))
        .expect()
        .spec(responseSpec())
        .when()
        .delete(ACCOUNT_USER_USER_ID, userIdValue));
  }

  @Step("Удалить книгу у пользователя из профиля")
  public ActionsResponce deleteBookProfileUser(String userId) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(
                cfg.oldUserNameValue(),
                cfg.oldPasswordValue())).body(
                deleteBookUserBody.DeleteBookUserBody(userId, IsbnBook.getInstance().isbnBookValue()))
            .expect()
            .spec(responseSpec())
            .when()
            .delete(BOOKSTORE_BOOK));
  }

  @Step("Добавить книгу пользователю в профиль")
  public ActionsResponce addBookProfileUser(String userName, String password, String userId) {
    return new ActionsResponce(
        given()
        .spec(restRequestSpecAuth(userName, password)
            .body(addBookUserBody.BodyAddBook(IsbnBook.getInstance().isbnBookValue(), userId)))
            .expect()
        .spec(responseSpec())
            .when()
            .post(BOOKSTORE_BOOKS));
  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser(String userId) {
    return new ActionsResponce(
        given().spec(
            restRequestSpecAuth(
                cfg.oldUserNameValue(),
                cfg.oldPasswordValue())
                .body(addBookUserBody.BodyAddBook(IsbnBook.getInstance().isbnBookValue(), userId))).expect()
        .spec(responseSpec())
            .when()
            .post(BOOKSTORE_BOOKS));
  }

  @Step("Запросить книгу")
  public ActionsResponce getBook(String isbnValue) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect()
            .spec(responseSpec())
            .when()
        .get(BOOKSTORE_ISBN, isbnValue));
  }

  @Step("Запросить данные о пользователе")
  public ActionsResponce getInfoUserProfile(String userName, String password, String userId) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(userName, password))
            .expect()
            .spec(responseSpec())
            .when()
            .get(ACCOUNT_USER_USER_ID, userId));
  }


  @Step("Получить авторизацию пользователя")
  public ActionsResponce authUser(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
        .body(requestAuthorizedBody.RequestAuthorizedBody(userName, password))
            .expect()
        .spec(responseSpec())
            .when()
            .post(AUTHORIZED));
  }

  @Step("Получить данные авторизации пользователя")
  public ActionsResponce userLoginInfo(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(loginBody.LoginBody(password, userName)).
            expect()
            .spec(responseSpec())
            .when()
            .post(ACCOUNT_LOGIN));
  }

}

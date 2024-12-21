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
import ru.demoqa.helpers.IsbnBook;
import ru.demoqa.models.LoginBody.LoginBody;
import ru.demoqa.models.authorizedBody.RequestAuthorizedBody;
import ru.demoqa.models.createUserBody.CreateUserBodyRequest;
import ru.demoqa.models.deleteBookUserBody.DeleteBookUserBody;
import ru.demoqa.models.addBookUserBody.AddBookUserBody;
import io.qameta.allure.Step;


public class StepsApi {

  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookUserBody addBookUserBody = new AddBookUserBody();
  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();
  LoginBody loginBody = new LoginBody();

  @Getter
  String userIdDellUser;

  @Step("Подготовка тестовых данных. Получение userId")
  public String getLoginUserId(String password, String userName) {
    String userId;
    userId = userLoginInfo(password, userName).getBodyFieldString("userId");
    return userId;
  }

  @Step("Очистка тестовых данных. Удаление нового пользователя")
  public void clearDeleteKillUser(String userId) {
    deleteUser(userId).shouldHave(statusCode(204));
  }

  @Step("Очистка тестовых данных. Удаление книги из профиля пользователя")
  public void clearDeleteBookFromUserProfile() {
    deleteBookProfileUser();
  }

  @Step("Подготовка тестовых данных. Добавление книги в профиль пользователю")
  public void testDataAddBookToProfileUser() {
    addBookProfileUser(getLoginUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue()),
        cfg.oldUserNameValue(), cfg.oldPasswordValue());
  }

  @Step("Подготовка тестовых данных.Создание нового пользователя")
  public void testDataAddNewUserAndUserId() {
    userIdDellUser = createNewAccount(cfg.killPasswordValue(), cfg.killUserNameValue(),
        cfg.killUserNameValue(), cfg.killPasswordValue()).shouldHave(statusCode(201))
        .getBodyFieldString("userID");
  }

  @Step("Создать нового пользователя")
  public ActionsResponce createNewAccount(String userName, String password, String userNameBody,
      String passwordBody) {
    return new ActionsResponce(given().spec(restRequestSpecAuth(userName, password))
        .body(createUserBodyRequest.RequestCreateUserBody(userNameBody, passwordBody)).expect()
        .spec(responseSpec()).when().post(ACCOUNT_USER));
  }

  @Step("Получить cписок книг")
  public ActionsResponce getListBook() {
    return new ActionsResponce(
        given().spec(restRequestSpec()).expect().spec(responseSpec()).when().get(BOOKSTORE_BOOKS));
  }

  @Step("Получить токен авторизации")
  public ActionsResponce getToken(String password, String userName) {
    return new ActionsResponce(given().spec(restRequestSpec())
        .body(requestAuthorizedBody.RequestAuthorizedBody(password, userName)).expect()
        .spec(responseSpec()).when().post(ACCOUNT_GENERATE_TOKEN));
  }


  @Step("Получить название книги")
  public ActionsResponce getNameBook() {
    return new ActionsResponce(
        given().spec(restRequestSpec()).expect().spec(responseSpec()).when().get(BOOKSTORE_BOOKS));
  }

  @Step("Удалить ранее созданного пользователя")
  public ActionsResponce deleteUser(String userIdValue) {
    return new ActionsResponce(given().spec(restRequestSpec().auth().preemptive()
            .basic(cfg.killUserNameValue(), cfg.killPasswordValue())).expect().spec(responseSpec())
        .when().delete(ACCOUNT_USER_USER_ID, userIdValue));
  }

  @Step("Удалить книгу у пользователя из профиля")
  public ActionsResponce deleteBookProfileUser() {
    return new ActionsResponce(
        given().spec(restRequestSpecAuth(cfg.oldUserNameValue(), cfg.oldPasswordValue())).body(
                deleteBookUserBody.DeleteBookUserBody(
                    getLoginUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue()),
                    IsbnBook.getInstance().isbnBookValue())).expect().spec(responseSpec()).when()
            .delete(BOOKSTORE_BOOK));
  }

  @Step("Добавить книгу пользователю в профиль")
  public ActionsResponce addBookProfileUser(String userId, String userName, String password) {
    return new ActionsResponce(given().spec(restRequestSpecAuth(userName, password).body(
            addBookUserBody.BodyAddBook(IsbnBook.getInstance().isbnBookValue(), userId))).expect()
        .spec(responseSpec()).when().post(BOOKSTORE_BOOKS));
  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser(String userId) {
    return new ActionsResponce(given().spec(
            restRequestSpecAuth(cfg.oldUserNameValue(), cfg.oldPasswordValue()).body(
                addBookUserBody.BodyAddBook(IsbnBook.getInstance().isbnBookValue(), userId))).expect()
        .spec(responseSpec()).when().post(BOOKSTORE_BOOKS));
  }

  @Step("Запросить книгу")
  public ActionsResponce getBook(String isbnValue) {
    return new ActionsResponce(given().spec(restRequestSpec()).expect().spec(responseSpec()).when()
        .get(BOOKSTORE_ISBN, isbnValue));
  }

  @Step("Запросить данные о пользователе")
  public ActionsResponce getInfoUserProfile(String userName, String password) {
    return new ActionsResponce(
        given().spec(restRequestSpecAuth(userName, password)).expect().spec(responseSpec()).when()
            .get(ACCOUNT_USER_USER_ID, getLoginUserId(password, userName)));
  }


  @Step("Получить авторизацию пользователя")
  public ActionsResponce authUser(String userName, String password) {
    return new ActionsResponce(given().spec(restRequestSpec())
        .body(requestAuthorizedBody.RequestAuthorizedBody(userName, password)).expect()
        .spec(responseSpec()).when().post(AUTHORIZED));
  }

  @Step("Получить данные авторизации пользователя")
  public ActionsResponce userLoginInfo(String password, String userName) {
    return new ActionsResponce(
        given().spec(restRequestSpec()).body(loginBody.LoginBody(password, userName)).expect()
            .spec(responseSpec()).when().post(ACCOUNT_LOGIN));
  }
}

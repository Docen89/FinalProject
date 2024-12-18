package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static api.Specifications.restRequestSpecAuth;
import static api.check.VerificationProcedures.statusCode;
import static ru.demoqa.test.configs.EndPoints.ACCOUNT_USER_USER_ID;
import static ru.demoqa.test.configs.EndPoints.BOOKSTORE_BOOK;
import static ru.demoqa.test.configs.EndPoints.BOOKSTORE_BOOKS;
import static ru.demoqa.test.configs.EndPoints.BOOKSTORE_ISBN;
import static io.restassured.RestAssured.given;
import static ru.demoqa.test.configs.EndPoints.ACCOUNT_GENERATE_TOKEN;
import static ru.demoqa.test.configs.EndPoints.ACCOUNT_LOGIN;
import static ru.demoqa.test.configs.EndPoints.ACCOUNT_USER;
import static ru.demoqa.test.test.BaseTest.cfg;

import lombok.Getter;
import templates.request.authorizedBody.RequestAuthorizedBody;
import templates.request.createUserBody.CreateUserBodyRequest;
import api.ActionsResponce;
import templates.request.deleteBookUserBody.DeleteBookUserBody;
import templates.request.addBookOldUserBody.AddBookOldUserBody;
import io.qameta.allure.Step;



public class StepsApi {
  @Getter
  private static String userIdValue;
  @Getter
  private static String isbnValue;
  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();
  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();



  @Step("Очистка тестовых данных. Удаление нового пользователя")
  public void clearDeleteNewUser(){
    getUserIdValue(cfg.newPasswordValue(), cfg.newUserNameValue());
    deleteUser().shouldHave(statusCode(204));
  }

  @Step("Очистка тестовых данных. Удаление книги из профиля пользователя")
  public void clearDeleteBookFromUserProfile(){
    deleteBookProfileUser();
  }

  @Step("Подготовка тестовых данных. Добавление книги в профиль пользователю")
  public void testDataAddBookToProfileUser(){
    getUserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    isbnValue();
    addBookProfileUser();
  }

  @Step("Подготовка тестовых данных.Создание нового пользователя")
  public void testDataAddNewUserAndUserId(){
    createNewAccount();
    getUserIdValue(cfg.newPasswordValue(), cfg.newUserNameValue());
  }

  @Step("Создать нового пользователя")
  public ActionsResponce createNewAccount() {
    return new ActionsResponce(given()
        .spec(restRequestSpecAuth(cfg.newUserNameValue(), cfg.newPasswordValue()))
        .body(createUserBodyRequest.RequestCreateUserBody())
        .expect()
        .spec(responseSpec())
        .when()
        .post(ACCOUNT_USER));
  }

  @Step("Вспомогательные шаги.Получить IsbnValue")
  public void isbnValue() {
    isbnValue = getListBook().getBodyFieldString("books[0].isbn");
  }

  @Step("Вспомогательные шаги.Получить userId")
  public void getUserIdValue(String password, String userName) {
    userIdValue = authorization(password,
        userName).getBodyFieldString("userId");
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
  public ActionsResponce getToken(String password, String userName) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(requestAuthorizedBody.RequestAuthorizedBody(password, userName))
            .expect()
            .spec(responseSpec())
            .when()
            .post(ACCOUNT_GENERATE_TOKEN));
  }

  @Step("Авторизоваться под пользователем")
  public ActionsResponce authorization(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(requestAuthorizedBody.RequestAuthorizedBody(userName, password))
            .expect()
            .spec(responseSpec())
            .when()
            .post(ACCOUNT_LOGIN));
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
  public ActionsResponce deleteUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth()
                .preemptive()
                .basic(cfg.newUserNameValue(),
                    cfg.newPasswordValue()))
            .expect()
            .spec(responseSpec())
            .when()
            .delete(ACCOUNT_USER_USER_ID,userIdValue));
  }

  @Step("Удалить книгу у пользователя из профиля")
  public ActionsResponce deleteBookProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(cfg.oldUserNameValue(),
                cfg.oldPasswordValue()))
            .body(deleteBookUserBody.DeleteBookUserBody(
                userIdValue,
                isbnValue))
            .expect()
            .spec(responseSpec())
            .when()
            .delete(BOOKSTORE_BOOK));
  }

  @Step("Добавить книгу пользователю в профиль")
  public ActionsResponce addBookProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(cfg.oldUserNameValue(),
                cfg.oldPasswordValue())
                .body(addBookOldUserBody.BodyAddBook(
                    isbnValue,
                    userIdValue)))
            .expect()
            .spec(responseSpec())
            .when()
            .post(BOOKSTORE_BOOKS));
  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(cfg.oldUserNameValue(),
                cfg.oldPasswordValue())
                .body(addBookOldUserBody.BodyAddBook(
                    isbnValue,
                    userIdValue)))
            .expect()
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
            .get(BOOKSTORE_ISBN,isbnValue));
  }

  @Step("Запросить данные о пользователе")
  public ActionsResponce getInfoUser(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpecAuth(userName, password))
            .expect().spec(responseSpec())
            .when()
            .get(ACCOUNT_USER_USER_ID,userIdValue));
  }

}
package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static configs.EndPoints.ACCOUNT_USER;
import static configs.EndPoints.BOOKSTORE_BOOK;
import static configs.EndPoints.BOOKSTORE_BOOKS;
import static configs.EndPoints.BOOKSTORE_ISBN;
import static io.restassured.RestAssured.given;
import static test.BaseTest.cfg;

import api.ActionsResponce;
import io.qameta.allure.Step;
import template.request.deleteBookUserBody.DeleteBookUserBody;
import template.request.addBookOldUserBody.AddBookOldUserBody;
import steps.api.LowerStepsApi;

public class UpperStepsApi {


  LowerStepsApi lowerStepsApi = new LowerStepsApi();
  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();


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
            .delete(ACCOUNT_USER + lowerStepsApi.UserIdValue(cfg.newPasswordValue(),
                cfg.newUserNameValue())));
  }

  @Step("Удалить книгу у пользователя из профиля")
  public ActionsResponce deleteBookProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth()
                .preemptive()
                .basic(cfg.oldUserNameValue(),
                    cfg.oldPasswordValue()))
            .body(deleteBookUserBody.DeleteBookUserBody(
                lowerStepsApi.UserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue()),
                lowerStepsApi.realIsbnValue()))
            .expect()
            .spec(responseSpec())
            .when()
            .delete(BOOKSTORE_BOOK));
  }

  @Step("Добавить книгу пользователю в профиль")
  public ActionsResponce addBookProfileUser(String isbnValue) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth()
                .preemptive()
                .basic(cfg.oldUserNameValue(),
                    cfg.oldPasswordValue())
                .body(addBookOldUserBody.BodyAddBook(
                    isbnValue,
                    lowerStepsApi.UserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue()))))
            .expect()
            .spec(responseSpec())
            .when()
            .post(BOOKSTORE_BOOKS));
  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth().preemptive()
                .basic(cfg.oldUserNameValue(),
                    cfg.oldPasswordValue())
                .body(addBookOldUserBody.BodyAddBook(
                    lowerStepsApi.realIsbnValue(),
                    lowerStepsApi.UserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue()))))
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
            .get(BOOKSTORE_ISBN + isbnValue));
  }

  @Step("Запросить данные о пользователе")
  public ActionsResponce getInfoUser(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth().preemptive()
                .basic(userName, password))
            .expect().spec(responseSpec())
            .when()
            .get(ACCOUNT_USER + lowerStepsApi.UserIdValue(cfg.oldPasswordValue(),
                cfg.oldUserNameValue())));
  }
}

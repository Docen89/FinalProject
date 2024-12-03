package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static io.restassured.RestAssured.given;
import static test.BaseTest.cfg;

import api.ActionsResponce;
import io.qameta.allure.Step;
import template.request.deleteBookUserBody.DeleteBookUserBody;
import template.request.addBookOldUserBody.AddBookOldUserBody;
import steps.api.LowerStepsApi;

public class UpperStepsApi {

  public String UserIdValue;
  LowerStepsApi lowerStepsApi = new LowerStepsApi();
  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();

  @Step("получить userId")
  public void getUserId(String password, String userName) {
    UserIdValue = lowerStepsApi.authorization(password, userName).getBodyFieldString("userId");

  }

  @Step("Получить название книги")
  public ActionsResponce getNameBook() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect()
            .spec(responseSpec())
            .when()
            .get("BookStore/v1/Book?ISBN=" + cfg.realIsbnValue()));

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
            .delete("Account/v1/User/" + UserIdValue));

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
            .body(deleteBookUserBody.completionDeleteBookUserBody(
                UserIdValue,
                cfg.realIsbnValue()))
            .expect()
            .spec(responseSpec())
            .when()
            .delete("BookStore/v1/Book"));

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
                .body(addBookOldUserBody.completionBodyAddBook(
                    isbnValue,
                    UserIdValue)))
            .expect()
            .spec(responseSpec())
            .when()
            .post("/BookStore/v1/Books"));

  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser() {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec()
                .auth().preemptive()
                .basic(cfg.oldUserNameValue(),
                    cfg.oldPasswordValue())
                .body(addBookOldUserBody.completionBodyAddBook(
                    cfg.realIsbnValue(), UserIdValue)))
            .expect()
            .spec(responseSpec())
            .when()
            .post("/BookStore/v1/Books"));

  }

  @Step("Запросить книгу")
  public ActionsResponce getBook(String isbnValue) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect()
            .spec(responseSpec())
            .when()
            .get("BookStore/v1/Book?ISBN=" + isbnValue));

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
            .get("Account/v1/User/" + UserIdValue));

  }
}

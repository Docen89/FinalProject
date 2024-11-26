package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static io.restassured.RestAssured.given;
import static test.BaseTest.cfg;

import api.ActionsResponce;
import io.qameta.allure.Step;
import model.response.createUser.ResponseCreateNewUser;
import template.request.deleteBookUserBody.DeleteBookUserBody;
import template.request.addBookOldUserBody.AddBookOldUserBody;
import steps.api.LowerStepsApi;

public class UpperStepsApi {
  String oldUserIdValue;
  String newUserIdValue;
  LowerStepsApi lowerStepsApi = new LowerStepsApi();
  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();

  @Step("получаем userIdNewUser")
  public void addUserIdNewUser(){
    newUserIdValue= lowerStepsApi.authorization(cfg.newPasswordValue(),cfg.newUserNameValue()).getBodyFieldString("userId");
  }

  @Step("получаем userIdOldUser")
  public void addUserIdOldUser(){
    oldUserIdValue= lowerStepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue()).getBodyFieldString("userId");
  }

  @Step("Удалить ранее созданного пользователя")
  public ActionsResponce deleteUser(){
    return  new ActionsResponce(
        given()
            .spec(restRequestSpec().auth().preemptive().basic(cfg.newUserNameValue(),
            cfg.newPasswordValue()))
            .expect().spec(responseSpec())
            .when()
            .delete("Account/v1/User/"+newUserIdValue));
  }

  @Step("Удалить книгу у пользователя из профиля")
  public  ActionsResponce deleteBookProfileUser(){
    return new ActionsResponce(
        given()
            .spec(restRequestSpec().auth().preemptive().basic(cfg.oldUserNameValue(),
            cfg.oldPasswordValue()))
            .body(deleteBookUserBody.completionDeleteBookUserBody(
                oldUserIdValue,
                cfg.realIsbnValue()))
            .expect().spec(responseSpec())
            .when()
            .delete("BookStore/v1/Book"));
  }

  @Step("Добавить книгу пользователю в профиль")
  public  ActionsResponce addBookProfileUser(){
    return new ActionsResponce(
        given()
            .spec(restRequestSpec().auth().preemptive().basic(cfg.oldUserNameValue(),
                cfg.oldPasswordValue())
                .body(addBookOldUserBody.completionBodyAddBook(
                cfg.realIsbnValue(),
                oldUserIdValue)))
            .expect().spec(responseSpec())
            .when()
            .post("/BookStore/v1/Books"));

  }

    @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
    public ActionsResponce checkMessageResponseRepeatedAddBooKProfileUser(){
        return new ActionsResponce(
            given()
                .spec(restRequestSpec().auth().preemptive().basic(cfg.oldUserNameValue(),
                    cfg.oldPasswordValue())
                    .body(addBookOldUserBody.completionBodyAddBook(
                cfg.realIsbnValue(), oldUserIdValue)))
                .expect().spec(responseSpec())
                .when()
                .post("/BookStore/v1/Books"));

    }

  @Step("Запросить существующую книгу")
  public ActionsResponce getRealBook(){
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect().spec(responseSpec())
            .when()
            .get("BookStore/v1/Book?ISBN="+cfg.realIsbnValue()));

  }

  @Step("Запросить несуществующую книгу")
  public ActionsResponce getNoRealBook(){
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .expect().spec(responseSpec())
            .when()
            .get("BookStore/v1/Book?ISBN="+cfg.notRealIsbnValue()));

  }

}

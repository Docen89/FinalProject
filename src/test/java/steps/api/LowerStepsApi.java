package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static configs.EndPoints.BOOKSTORE_BOOKS;
import static io.restassured.RestAssured.given;
import static configs.EndPoints.ACCOUNT_GENERATE_TOKEN;
import static configs.EndPoints.ACCOUNT_LOGIN;
import static configs.EndPoints.ACCOUNT_USER;
import static test.BaseTest.cfg;

import io.qameta.allure.Step;
import java.templates.request.authorizedBody.RequestAuthorizedBody;
import java.templates.request.createUserBody.CreateUserBodyRequest;
import api.ActionsResponce;


public class LowerStepsApi  {

  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();


  @Step("Создать нового пользователя")
  public ActionsResponce createNewAccount() {
    return new ActionsResponce(given()
        .spec(restRequestSpec()
            .auth()
            .preemptive()
            .basic(cfg.newUserNameValue(), cfg.newPasswordValue()))
        .body(createUserBodyRequest.RequestCreateUserBody())
        .expect()
        .spec(responseSpec())
        .when()
        .post(ACCOUNT_USER));
  }

  @Step("Получить userId")
  public String UserIdValue(String password, String userName) {
    String UserIdValue;
    UserIdValue = authorization(password, userName).getBodyFieldString("userId");
    return UserIdValue;
  }

  @Step("Получить realIsbnValue")
  public String realIsbnValue() {
    String realIsbnValue;
    realIsbnValue = getListBook().getBodyFieldString("books[0].isbn");
    return realIsbnValue;
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

}
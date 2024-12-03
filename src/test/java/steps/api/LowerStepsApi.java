package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import template.request.authorizedBody.RequestAuthorizedBody;
import template.request.createUserBody.CreateUserBodyRequest;
import test.BaseTest;
import api.ActionsResponce;


public class LowerStepsApi extends BaseTest {

  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();


  @Step("Создать нового пользователя")
  public ActionsResponce createNewAccount() {
    return new ActionsResponce(given()
        .spec(restRequestSpec()
            .auth()
            .preemptive()
            .basic(cfg.newUserNameValue(), cfg.newPasswordValue()))
        .body(createUserBodyRequest.completionRequestCreateUserBody())
        .expect()
        .spec(responseSpec())
        .when()
        .post("/Account/v1/User/"));

  }


  @Step("Получить токен авторизации")
  public ActionsResponce getToken(String password, String userName) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(requestAuthorizedBody.completionRequestAuthorizedBody(password, userName))
            .expect()
            .spec(responseSpec())
            .when()
            .post("/Account/v1/GenerateToken"));

  }

  @Step("Авторизоваться под пользователем")
  public ActionsResponce authorization(String userName, String password) {
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(requestAuthorizedBody.completionRequestAuthorizedBody(userName, password))
            .expect()
            .spec(responseSpec())
            .when()
            .post("Account/v1/Login"));


  }

}
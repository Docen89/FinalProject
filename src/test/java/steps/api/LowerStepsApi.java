package steps.api;

import static api.Specifications.responseSpec;
import static api.Specifications.restRequestSpec;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import template.request.authorizedBody.RequestAuthorizedBody;
import template.request.createUserBody.CreateUserBodyRequest;
import test.BaseTest;
import api.ActionsResponce;


public class LowerStepsApi extends BaseTest {

  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();




  @Step("Создать нового пользователя")
  public ActionsResponce  createNewAccount() {
    return new ActionsResponce(given()
        .spec(restRequestSpec().auth().preemptive()
            .basic(cfg.newUserNameValue(), cfg.newPasswordValue()))
        .body(createUserBodyRequest.completionRequestCreateUserBody())
        .expect().spec(responseSpec())
        .when()
        .post("/Account/v1/User/"));
  }


  @Step("Получить токен авторизации")
  public ActionsResponce getToken(){
    return   new ActionsResponce(
        given()
        .spec(restRequestSpec())
        .body(requestAuthorizedBody.completionRequestAuthorizedBody(cfg.newPasswordValue(),
                cfg.newUserNameValue()))
            .expect().spec(responseSpec())
            .when()
        .post("/Account/v1/GenerateToken"));
  }

  @Step("Авторизоваться под пользователем")
  public ActionsResponce authorization(){
    return new ActionsResponce(
        given()
            .spec(restRequestSpec())
            .body(requestAuthorizedBody.completionRequestAuthorizedBody(cfg.oldPasswordValue(),
                cfg.oldUserNameValue()))
            .expect().spec(responseSpec())
            .when()
            .post("Account/v1/Login"));

  }

}
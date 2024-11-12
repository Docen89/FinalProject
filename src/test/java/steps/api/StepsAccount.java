package steps.api;

import static org.hamcrest.Matchers.equalTo;

import static test.BaseTest.cfg;

import api.RestAssuredHaveBodyRequestAuthPost;
import api.RestAssuredHaveBodyRequestNoAuthPost;
import api.RestAssuredNoBodyRequestDelete;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import model.response.createUser.ResponseCreateNewUser;
import template.completion.request.authorizedBody.RequestAuthorizedBody;
import template.completion.request.createUserBody.CreateUserBodyRequest;


public class StepsAccount {

  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();
  String newUserIdValue;


  @Step("Создаем нового пользователя")
  public void createNewAccount() throws JsonProcessingException {
    RestAssuredHaveBodyRequestAuthPost restAssuredHaveBodyRequestAuthPost = new RestAssuredHaveBodyRequestAuthPost()
        .post("/Account/v1/User/",
            createUserBodyRequest.completionRequestCreateUserBody(),
            cfg.newUserNameValue(),
            cfg.newPasswordValue())
        .responseStatusCode(201)
        .responseJson("username", equalTo(cfg.newUserNameValue()));
    String jsonResponseCreateUserBody = restAssuredHaveBodyRequestAuthPost.getResponse().body()
        .print();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseCreateNewUser responseCreateNewUser = objectMapper.readValue(jsonResponseCreateUserBody,
        ResponseCreateNewUser.class);
    newUserIdValue = responseCreateNewUser.getUserID();

  }

  @Step("Получаем токен авторизации")
  public void getToken() {
    new RestAssuredHaveBodyRequestNoAuthPost()
        .post("/Account/v1/GenerateToken", requestAuthorizedBody.completionRequestAuthorizedBody())
        .responseStatusCode(200)
        .responseJson("result", equalTo("User authorized successfully."));

  }

  @Step("Авторизуемся")
  public void authorization() {
    new RestAssuredHaveBodyRequestNoAuthPost()
        .post("Account/v1/Login",requestAuthorizedBody.completionRequestAuthorizedBody())
        .responseStatusCode(200)
        .responseJson("username",equalTo(cfg.oldUserNameValue()));

  }


  @Step("Удаляем ранее созданного пользователя")
  public void deleteNewUser() {
    new RestAssuredNoBodyRequestDelete()
        .delete("/Account/v1/User/" + newUserIdValue,
            cfg.newUserNameValue(),
            cfg.newPasswordValue())
        .responseStatusCode(204);

  }

}
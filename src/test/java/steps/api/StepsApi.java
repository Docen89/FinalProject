package steps.api;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


import api.RestAssuredHaveBodyRequestAuthPost;
import api.RestAssuredHaveBodyRequestNoAuthPost;
import api.RestAssuredNoBodyRequestDelete;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import model.response.createUser.ResponseCreateNewUser;
import java.template.request.authorizedBody.RequestAuthorizedBody;
import java.template.request.createUserBody.CreateUserBodyRequest;
import test.BaseTest;
import model.response.Auth.ResponseAuthBody;
import java.template.request.addBookOldUserBody.AddBookOldUserBody;
import java.template.request.deleteBookUserBody.DeleteBookUserBody;


public class StepsApi extends BaseTest {

  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();
  DeleteBookUserBody deleteBookUserBody = new DeleteBookUserBody();
  AddBookOldUserBody addBookOldUserBody = new AddBookOldUserBody();
  String newUserIdValue;
  String oldUserIdValue;


  @Step("Создать нового пользователя")
  public void createNewAccount() throws JsonProcessingException {
    RestAssuredHaveBodyRequestAuthPost restAssuredHaveBodyRequestAuthPost = new RestAssuredHaveBodyRequestAuthPost()
        .post("/Account/v1/User/",
            createUserBodyRequest.completionRequestCreateUserBody(),
            cfg.newUserNameValue(),
            cfg.newPasswordValue())
        .responseStatusCode(201)
        .responseJson("username", equalTo(cfg.newUserNameValue()));
    String jsonResponseCreateNewUserBody = restAssuredHaveBodyRequestAuthPost.getResponse().body()
        .print();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseCreateNewUser responseCreateNewUser = objectMapper.readValue(
        jsonResponseCreateNewUserBody,
        ResponseCreateNewUser.class);
    newUserIdValue = responseCreateNewUser.getUserID();

  }

  @Step("Получить токен авторизации")
  public void getToken() {
    new RestAssuredHaveBodyRequestNoAuthPost()
        .post("/Account/v1/GenerateToken",
            requestAuthorizedBody.completionRequestAuthorizedBody(cfg.newPasswordValue(),
                cfg.newUserNameValue()))
        .responseStatusCode(200)
        .responseJson("result", equalTo("User authorized successfully."));

  }

  @Step("Авторизоваться под пользователем")
  public void authorization() throws JsonProcessingException {
    RestAssuredHaveBodyRequestNoAuthPost restAssuredHaveBodyRequestNoAuthPost = new RestAssuredHaveBodyRequestNoAuthPost()
        .post("Account/v1/Login",
            requestAuthorizedBody.completionRequestAuthorizedBody(cfg.oldPasswordValue(),
                cfg.oldUserNameValue()))
        .responseStatusCode(200)
        .responseJson("username", equalTo(cfg.oldUserNameValue()));
    String jsonResponseAuthOldUserBody = restAssuredHaveBodyRequestNoAuthPost.getResponse().body()
        .print();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseAuthBody responseAuthBody = objectMapper.readValue(jsonResponseAuthOldUserBody,
        model.response.Auth.ResponseAuthBody.class);
    oldUserIdValue = responseAuthBody.getUserId();
  }


  @Step("Удалить ранее созданного пользователя")
  public void deleteNewUser() {
    new RestAssuredNoBodyRequestDelete()
        .delete("/Account/v1/User/" + newUserIdValue,
            cfg.newUserNameValue(),
            cfg.newPasswordValue())
        .responseStatusCode(204);

  }

  @Step("Добавить книгу пользователю в профиль")
  public void addBookProfileUser() {
    new RestAssuredHaveBodyRequestAuthPost()
        .post("/BookStore/v1/Books",
            addBookOldUserBody.completionBodyAddBook(
                cfg.realIsbnValue(),
                oldUserIdValue),
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue())
        .responseStatusCode(201);

  }

  @Step("Проверить сообщение в ответе на запрос повторного добавления книги в профиль")
  public void checkMessageResponseRepeatedAddBooKProfileUser() {
    new RestAssuredHaveBodyRequestAuthPost()
        .post("/BookStore/v1/Books",
            addBookOldUserBody.completionBodyAddBook(
                cfg.realIsbnValue(),
                oldUserIdValue),
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue())
        .responseStatusCode(400)
        .responseJson("message", containsString("ISBN already present in the User's Collection!"))
        .responseJson("code", containsString("1210"));

  }

  @Step("Удалить книгу у пользователя из профиля")
  public void deleteBookProfileUser() {
    new api.RestAssuredHaveBodyRequestDelete()
        .delete("BookStore/v1/Book",
            cfg.oldUserNameValue(),
            cfg.oldPasswordValue(),
            deleteBookUserBody.completionDeleteBookUserBody(
                oldUserIdValue,
                cfg.realIsbnValue()))
        .responseStatusCode(204);

  }

  @Step("Запросить существующую книгу")
  public void getRealBook() {
    new api.RestAssuredNoBodyRequestGet()
        .get("BookStore/v1/Book?ISBN=" + cfg.realIsbnValue())
        .responseStatusCode(200)
        .responseJson("isbn", equalTo("9781449337711"))
        .responseJson("title", equalTo("Designing Evolvable Web APIs with ASP.NET"));

  }

  @Step("Запросить несуществующую книгу")
  public void getNoRealBook() {
    new api.RestAssuredNoBodyRequestGet()
        .get("BookStore/v1/Book?ISBN=" + cfg.notRealIsbnValue())
        .responseStatusCode(400)
        .responseJson("message", equalTo("ISBN supplied is not available in Books Collection!"))
        .responseJson("code", equalTo("1205"));

  }


}
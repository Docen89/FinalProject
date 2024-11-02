package StepsApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Api.Account;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import java.util.Map;
import lombok.Getter;

@Getter

public class StepsAccount {

  Account account = new Account();
  String checkNameValueResponce;
  String checkStatusValueResponseLogin;
  String checkStatusDeleteUser;
  String userIdNewUser;


  @Step("Создаем нового пользователя")
  public void createNewAccount() {
    Map<String, String> userIdAndUserNameAndBooksAndStatus = account.createNewUser();
    checkNameValueResponce = userIdAndUserNameAndBooksAndStatus.get("userNameValue");
    userIdNewUser = userIdAndUserNameAndBooksAndStatus.get("userIdValue");
  }

  @Step("Проверяем  логин  у нового пользователя")
  public void checkLoginNewUser() {
    assertEquals("Docen90", checkNameValueResponce);
  }

  @Step("Авторизуемся")
  public void authorization() {
    Map<String, String> tokenAndExpiresAndUserIdAndStatusValue = account.authorized();
    checkStatusValueResponseLogin = tokenAndExpiresAndUserIdAndStatusValue.get("status");
  }

  @Step("Проверяем статус ответа на авторизацию зарегистрированным пользователем")
  public void checkStatusResponseAuthOldUser() {
    assertEquals("200", checkStatusValueResponseLogin);
  }

  @Step("Удаляем ранее созданного пользователя")
  public void deleteNewUser() {
    Map<String, String> responseDeleteUser = account.deleteUser();
    checkStatusDeleteUser = responseDeleteUser.get("StatusValue");
  }

  @Step("Проверяем статус ответа на запрос об удаление пользователя")
  public void checkResponseStatusDeleteUser() {
    assertEquals("204", checkStatusDeleteUser);
  }
}
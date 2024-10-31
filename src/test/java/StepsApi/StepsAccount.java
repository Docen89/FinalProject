package StepsApi;

import Api.Account;
import io.qameta.allure.Step;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter

public class StepsApi {

  String checkNameValueResponce;
  String checkStatusValueResponseLogin;

  Account account = new Account();

  @Step("Создаем нового пользователя")
  public void createNewAccount() {
    Map<String, String> userIdAndUserNameAndBooksAndStatus = account.createNewUser("Docen90",
        "Docen1313!", "https://demoqa.com/Account/v1/User");
    checkNameValueResponce = userIdAndUserNameAndBooksAndStatus.get("userNameValue");
  }

  @Step("Авторизуемся")
  public void authorization() {
    Map<String, String> tokenAndExpiresAndUserIdAndStatusValue = account.authorized("Docen89",
        "Docen1313!", "https://demoqa.com/Account/v1/Login");
    checkStatusValueResponseLogin = tokenAndExpiresAndUserIdAndStatusValue.get("status");
  }

  @Step("Удаляем ранее созданного пользователя")
  public void deleteOldUser() {
    Map<String, String> responseDeleteUser = account.deleteUser("Docen90",
        "Docen1313!", "https://demoqa.com/Account/v1/User",)
  }
}
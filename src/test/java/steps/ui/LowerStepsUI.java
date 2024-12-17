package steps.ui;

import static test.BaseTest.cfg;
import io.qameta.allure.Step;
import lombok.Getter;
import api.ActionsResponce;

public class LowerStepsUI {

  static steps.api.StepsApi stepsApi = new steps.api.StepsApi();
  @Getter
  private static String userIDCookieValueNew;
  @Getter
  private static String tokenCookieValueNew;
  @Getter
  private static String expiresCookieValueNew;


  @Step("Вспомогательные шаги.Создать нового пользователя")
  public void creatNewUser() {
    stepsApi.createNewAccount();
    stepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue());
  }

  @Step("Вспомогательные шаги.Получить Cokkie нового пользователя")
  public static  void addCookieNewUser() {
    ActionsResponce responce = stepsApi.authorization(cfg.newPasswordValue(),
        cfg.newUserNameValue());
    userIDCookieValueNew = responce.getBodyFieldString("userId");
    tokenCookieValueNew = responce.getBodyFieldString("token");
    expiresCookieValueNew = responce.getBodyFieldString("expires");
  }

  @Step("Вспомогательные шаги.Добавить книгу в профиль к пользователю")
  public void addBookUser() {
    stepsApi.getUserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsApi.addBookProfileUser();
  }

  @Step("Вспомогательные шаги.Удалить книгу у пользователя")
  public void deleteBookUser() {
    stepsApi.deleteBookProfileUser();
  }

}

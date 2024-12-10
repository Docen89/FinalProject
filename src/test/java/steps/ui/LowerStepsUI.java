package steps.ui;

import static com.codeborne.selenide.Selenide.open;
import static test.BaseTest.cfg;


import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import steps.api.LowerStepsApi;
import steps.api.UpperStepsApi;
import api.ActionsResponce;

public class LowerStepsUI {

  UpperStepsApi upperStepsApi = new UpperStepsApi();
  static LowerStepsApi lowerStepsApi = new LowerStepsApi();
  public static String userIDCookieValueNew;
  public static String tokenCookieValueNew;
  public static String expiresCookieValueNew;


  @Step("Открыть страницу сайта с Cookie")
  public void openSiteWithCookie(String endPoint, String userIdValue, String tokenValue,
      String expiresValue) {
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("userID", userIdValue)));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", tokenValue)));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", expiresValue)));
    open(endPoint);
  }

  @Step("Создать нового пользователя")
  public void creatNewUser() {
    lowerStepsApi.createNewAccount();
    lowerStepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue());
  }

  @Step("Получить Cokkie нового пользователя")
  public void addCookieNewUser() {
    ActionsResponce responce = lowerStepsApi.authorization(cfg.newPasswordValue(),
        cfg.newUserNameValue());
    userIDCookieValueNew = responce.getBodyFieldString("userId");
    tokenCookieValueNew = responce.getBodyFieldString("token");
    expiresCookieValueNew = responce.getBodyFieldString("expires");
  }

  @Step("Добавить книгу в профиль к пользователю")
  public void addBookUser() {
    lowerStepsApi.UserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(lowerStepsApi.realIsbnValue());
  }

  @Step("Удалить книгу у пользователя")
  public void deleteBookUser() {
    upperStepsApi.deleteBookProfileUser();
  }

}

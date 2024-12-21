package ru.demoqa.steps.ui;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.demoqa.page.LoginPage;
import ru.demoqa.steps.api.StepsApi;
import ru.demoqa.test.BaseTest;


public class StepsLoginPage extends BaseTest {

  LoginPage loginPage = new LoginPage();
  StepsApi stepsApi = new StepsApi();

  @Step("Открыть страницу Book Store")
  public void openBookStore(String endpoint) {
    Selenide.clearBrowserCookies();
    Selenide.open(endpoint);
  }

  @Step("Ввод имени пользователя")
  public void inputUserName(String nameValue) {
    loginPage.userName(nameValue).scrollTo().setValue(nameValue);
  }

  @Step("Ввод пароля пользователя")
  public void inputPasswordUser(String passwordValue) {
    loginPage.userPassword(passwordValue).setValue(passwordValue);
  }

  @Step("Клик по кнопке 'Login'")
  public void clickButtonLogin() {
    loginPage.loginButton().scrollTo().click();
  }

  @Step("Клик по кнопке 'New User'")
  public void clickButtonNewUser() {
    loginPage.newUserButton().scrollTo().click();
  }

  @Step("Проверить наличие на странице сообщения 'Invalid username or password!'")
  public void checkMessagePage() {
    loginPage.messageText().shouldHave(text("Invalid username or password!"));
  }

  @Step("Проверить наличие  в профиле кнопки 'Log Out'")
  public void checkButtonLogOut() {
    loginPage.buttonLogOut().shouldHave(text("Log out"));
  }

  @Step("Открыть страницу сайта с Cookie нового пользователя")
  public void openSiteWithCookieUser(String endPointTwo, String userID, String token,
      String expires) {
    stepsApi.authUser(cfg.guiNewUserNameValue(), cfg.guiNewPasswordValue());
    WebDriverRunner.getWebDriver().manage().addCookie((new Cookie("userID", userID)));
    WebDriverRunner.getWebDriver().manage().addCookie((new Cookie("token", token)));
    WebDriverRunner.getWebDriver().manage().addCookie((new Cookie("expires", expires)));
    open(endPointTwo);
  }

}

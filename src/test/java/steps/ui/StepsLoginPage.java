package steps.ui;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import steps.ui.LowerStepsUI;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import page.LoginPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import test.BaseTest;

public class StepsLoginPage extends BaseTest {
  LoginPage loginPage = new LoginPage();

  @Step("Открыть страницу Book Store")
  public void openBookStore(String endpoint) {
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

  @Step("Открыть страницу сайта с Cookie старого пользователя")
  public void openSiteWithCookieOldUser(String endPoint) {
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("userID",BaseTest.getUserIDCookieValueOld())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", BaseTest.getTokenCookieValueOld())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", BaseTest.getTokenCookieValueOld())));
    open(endPoint);
  }

  @Step("Открыть страницу сайта с Cookie нового пользователя")
  public void openSiteWithCookieNewUser(String endPoint) {
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("userID", LowerStepsUI.getUserIDCookieValueNew())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", LowerStepsUI.getTokenCookieValueNew())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", LowerStepsUI.getExpiresCookieValueNew())));
    open(endPoint);
  }

}

package ru.demoqa.steps.api;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.WebDriverRunner;

import org.openqa.selenium.Cookie;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.demoqa.helpers.CookieOldUser;
import ru.demoqa.helpers.GuiUserCookie;
import ru.demoqa.page.LoginPage;
import ru.demoqa.test.BaseTest;


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
        .addCookie((new Cookie("userID", CookieOldUser.getInstance().getUserIdValueOldUser())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", CookieOldUser.getInstance().getTokenValueOldUser())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", CookieOldUser.getInstance().getExpiresValueOldUser())));
    open(endPoint);
  }

  @Step("Открыть страницу сайта с Cookie нового пользователя")
  public void openSiteWithCookieNewUser(String endPoint) {
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("userID", GuiUserCookie.getInstance().getGuiUserIdValueNewUser())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", GuiUserCookie.getInstance().getGuiTokenValueNewUser())));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", GuiUserCookie.getInstance().getGuiExpiresValueNewUser())));
    open(endPoint);
  }

}

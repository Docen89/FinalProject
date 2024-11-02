package StepsUi;

import com.codeborne.selenide.Configuration;
import lombok.Getter;
import PageObjects.LoginPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class StepsLoginPage {

  @Getter
  String buttomLogOutTextValue;
  String messegTextValue;
  LoginPage lgpage = new LoginPage();

  @Step("Открытие страницы Book Store")
  public void openBookStore(String url) {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "2560x1440";
    Selenide.open(url);
  }


  @Step("Вводим имя пользователя")
  public void inputUserName(String nameValue) {
    lgpage.userName(nameValue).sendKeys(nameValue);
  }

  @Step("Вводим пароль пользователя")
  public void inputPasswordUser(String passwordValue) {
    lgpage.userPassword(passwordValue).sendKeys(passwordValue);
  }

  @Step("Кликаем по кнопке Login")
  public void clickBottonLogin() {
    lgpage.loginButton().scrollTo().click();
  }

  @Step("Кликаем по кнопке New User")
  public void clickBottonNewUser() {
    lgpage.newUserButton().scrollTo().click();
  }

  @Step("Проверяем наличие на странице сообщения 'Invalid username or password!'")
  public String checkMessagePage() {
    messegTextValue = lgpage.messegText().getText();
    return messegTextValue;
  }

  @Step("Проверям наличие  в профиле кнопки 'Log Out'")
  public String checkButtonLogOut() {
    buttomLogOutTextValue = lgpage.buttonLogOut().getText();
    return buttomLogOutTextValue;
  }

  @Step("Чистим  кэш")
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
    Selenide.closeWebDriver();
  }


}

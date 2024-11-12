package steps.ui;

import lombok.Getter;
import page.LoginPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import test.BaseTest;

public class StepsLoginPage extends BaseTest {

  @Getter
  String buttonLogOutTextValue;
  String messegTextValue;
  LoginPage loginPage = new LoginPage();

  @Step("Открытие страницы Book Store")
  public void openBookStore(String endpoint) {
    Selenide.open(endpoint);
  }


  @Step("Вводим имя пользователя")
  public void inputUserName(String nameValue) {
    loginPage.userName(nameValue).scrollTo().sendKeys(nameValue);
  }

  @Step("Вводим пароль пользователя")
  public void inputPasswordUser(String passwordValue) {
    loginPage.userPassword(passwordValue).sendKeys(passwordValue);
  }

  @Step("Кликаем по кнопке Login")
  public void clickButtonLogin() {
    loginPage.loginButton().scrollTo().click();
  }

  @Step("Кликаем по кнопке New User")
  public void clickButtonNewUser() {
    loginPage.newUserButton().scrollTo().click();
  }

  @Step("Проверяем наличие на странице сообщения 'Invalid username or password!'")
  public String checkMessagePage() {
    messegTextValue = loginPage.messegText().getText();
    return messegTextValue;
  }

  @Step("Проверяем наличие  в профиле кнопки 'Log Out'")
  public void checkButtonLogOut() {
    buttonLogOutTextValue = loginPage.buttonLogOut().getText();
  }

  @Step("Чистим  кэш")
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
    Selenide.closeWebDriver();
  }


}

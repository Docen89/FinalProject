package steps.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import page.LoginPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import test.BaseTest;

public class StepsLoginPage extends BaseTest {

  String buttonLogOutTextValue;
  String messegTextValue;
  LoginPage loginPage = new LoginPage();

  @Step("Открыть страницу Book Store")
  public void openBookStore(String endpoint) {
    Selenide.open(endpoint);
  }


  @Step("Ввести имя пользователя")
  public void inputUserName(String nameValue) {
    loginPage.userName(nameValue).scrollTo().sendKeys(nameValue);
  }

  @Step("Ввести пароль пользователя")
  public void inputPasswordUser(String passwordValue) {
    loginPage.userPassword(passwordValue).sendKeys(passwordValue);
  }

  @Step("Кликнуть по кнопке Login")
  public void clickButtonLogin() {
    loginPage.loginButton().scrollTo().click();
  }

  @Step("Кликнуть по кнопке New User")
  public void clickButtonNewUser() {
    loginPage.newUserButton().scrollTo().click();
  }

  @Step("Проверить наличие на странице сообщения 'Invalid username or password!'")
  public  void  checkMessagePage() {
    messegTextValue =loginPage.messegText().getText();
    assertEquals("Invalid username or password!", messegTextValue);
  }

  @Step("Проверить наличие  в профиле кнопки 'Log Out'")
  public void checkButtonLogOut() {
    buttonLogOutTextValue = loginPage.buttonLogOut().getText();
    assertEquals("Log out", buttonLogOutTextValue);
  }

  @Step("Чистим  кэш")
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
    Selenide.closeWebDriver();
  }


}

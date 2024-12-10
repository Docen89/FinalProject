package steps.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    loginPage.userName(nameValue).scrollTo().sendKeys(nameValue);
  }

  @Step("Ввод пароля пользователя")
  public void inputPasswordUser(String passwordValue) {
    loginPage.userPassword(passwordValue).sendKeys(passwordValue);
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
    String messageTextValue;
    messageTextValue = loginPage.messageText().getText();
    assertEquals("Invalid username or password!", messageTextValue);
  }

  @Step("Проверить наличие  в профиле кнопки 'Log Out'")
  public void checkButtonLogOut() {
    String buttonLogOutTextValue;
    buttonLogOutTextValue = loginPage.buttonLogOut().getText();
    assertEquals("Log out", buttonLogOutTextValue);
  }

}

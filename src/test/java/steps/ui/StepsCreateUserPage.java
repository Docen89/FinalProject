package steps.ui;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

import io.qameta.allure.Step;
import lombok.Getter;
import page.CreateUserPage;

public class StepsCreateUserPage {

  @Getter
  String createUserMessageValue;
  String verifyMessageCaptchaValue;

  CreateUserPage cup = new CreateUserPage();

  @Step("Вводим имя нового пользователя")
  public void inputNewUserFirstName(String firstNameValue) {
    cup.firstName(firstNameValue).sendKeys(firstNameValue);
  }

  @Step("Вводим фамилию нового пользователя")
  public void inputNewUserLastName(String lastNameValue) {
    cup.lastName(lastNameValue).sendKeys(lastNameValue);
  }

  @Step("Вводим логин нового пользователя")
  public void inputNewUserName(String newUserNameValue) {
    cup.userName(newUserNameValue).sendKeys(newUserNameValue);
  }

  @Step("Вводим пароль нового пользователя")
  public void inputNewUserPassword(String newUserPasswordValue) {
    cup.Password(newUserPasswordValue).sendKeys(newUserPasswordValue);
  }

  @Step("Проверка Captcha")
  public void recaptchaTestClick() {
    switchTo().frame(
        $x("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]"));
    $("div.rc-anchor-content").click();
    switchTo().defaultContent();

  }

  @Step("Кликаем кнопку регистрация нового пользователя")
  public void clickButtonRegister() {
    cup.buttonRegister().scrollTo().click();
  }

  @Step("Проверяем уведомление о том, что пользователь зарегистрировался")
  public String userCreateMessage() {
    createUserMessageValue = cup.messageCreateUser(createUserMessageValue).getText();
    return createUserMessageValue;
  }

  @Step("Проверяем уведомление о том, что необходимо указать Captcha")
  public String verifyCaptchaMessage() {
    verifyMessageCaptchaValue = cup.messageVerifyCaptcha(verifyMessageCaptchaValue).getText();
    return verifyMessageCaptchaValue;
  }

  @Step("Не заполнено поле FirstName")
  public void validFirstName() {
    cup.noFirstName();
  }

  @Step("Не заполнено поле LastName")
  public void validLastName() {
    cup.noLastName();
  }


}

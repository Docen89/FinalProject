package steps.ui;


import io.qameta.allure.Step;
import page.CreateUserPage;

public class StepsCreateUserPage {


  CreateUserPage createUserPage = new CreateUserPage();

  @Step("Ввести имя нового пользователя")
  public void inputNewUserFirstName(String firstNameValue) {
    createUserPage.firstName(firstNameValue).sendKeys(firstNameValue);
  }

  @Step("Ввести фамилию нового пользователя")
  public void inputNewUserLastName(String lastNameValue) {
    createUserPage.lastName(lastNameValue).sendKeys(lastNameValue);
  }

  @Step("Ввести логин нового пользователя")
  public void inputNewUserName(String newUserNameValue) {
    createUserPage.userName(newUserNameValue).sendKeys(newUserNameValue);
  }

  @Step("Ввести пароль нового пользователя")
  public void inputNewUserPassword(String newUserPasswordValue) {
    createUserPage.Password(newUserPasswordValue).sendKeys(newUserPasswordValue);
  }


  @Step("Кликнуть кнопку регистрация нового пользователя")
  public void clickButtonRegister() {
    createUserPage.buttonRegister().scrollTo().click();
  }


  @Step("Не заполнено поле FirstName")
  public void validFirstName() {
    createUserPage.noFirstName();
  }

  @Step("Не заполнено поле LastName")
  public void validLastName() {
    createUserPage.noLastName();
  }


}

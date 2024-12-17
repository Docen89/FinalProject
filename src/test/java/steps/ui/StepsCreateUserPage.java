package steps.ui;


import io.qameta.allure.Step;
import page.CreateUserPage;

public class StepsCreateUserPage {


  CreateUserPage createUserPage = new CreateUserPage();

  @Step("Ввод имени нового пользователя")
  public void inputNewUserFirstName(String firstNameValue) {
    createUserPage.firstName(firstNameValue).setValue(firstNameValue);
  }

  @Step("Ввод фамилии нового пользователя")
  public void inputNewUserLastName(String lastNameValue) {
    createUserPage.lastName(lastNameValue).setValue(lastNameValue);
  }

  @Step("Ввод логина нового пользователя")
  public void inputNewUserName(String newUserNameValue) {
    createUserPage.userName(newUserNameValue).setValue(newUserNameValue);
  }

  @Step("Ввод пароля нового пользователя")
  public void inputNewUserPassword(String newUserPasswordValue) {
    createUserPage.Password(newUserPasswordValue).setValue(newUserPasswordValue);
  }

  @Step("Клик по кнопке 'Регистрация нового пользователя'")
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

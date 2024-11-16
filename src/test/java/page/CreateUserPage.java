package page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class CreateUserPage {

  public SelenideElement firstName(String firstNameValue) {
    return $x("//input[@id='firstname']").as("Поле'Имя'");
  }

  public SelenideElement lastName(String lastNameValue) {

    return $x("//input[@id='lastname']").as("Поле'Фамилия'");
  }

  public SelenideElement userName(String newUserNameValue) {
    return $x("//input[@id='userName']").as("Поле'Имя пользователя'");
  }

  public SelenideElement Password(String newUserPasswordValue) {
    return $x("//input[@id='password']").as("Поле'Пароль'");
  }

  public SelenideElement buttonBackToLogin() {
    return $x("//button[@id='gotologin']");
  }


  public SelenideElement buttonRegister() {
    return $x("//button[@id='register']").as("Кнопка'Регистрация'");
  }


  public SelenideElement noFirstName() {
    return $x("//*[contains(@class,'invalid form-control')]") .as("Валидация на поле 'Имя'");
  }

  public SelenideElement noLastName() {
    return $x("//*[contains(@class,'invalid form-control')]").as("Валидация на поле 'Фамилия'");
  }

}

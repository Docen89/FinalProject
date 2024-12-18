package ru.demoqa.page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class LoginPage {

  public SelenideElement userName(String nameValue) {
    return $x("//input[@id='userName']").as("поле 'Имя пользователя'");
  }

  public SelenideElement userPassword(String passwordValue) {
    return $x("//input[@id='password']").as("поле 'Пароль'");
  }

  public SelenideElement loginButton() {
    return $x("//button[@id='login']").as("кнопка 'Логин'");
  }

  public SelenideElement newUserButton() {
    return $x("//button[@id='newUser']").as("кнопка 'Новый пользователь'");
  }

  public SelenideElement buttonLogOut() {
    return $x("//button[@id='submit']").as("кнопка 'Выйти из личного кабинета'");
  }

  public SelenideElement messageText() {
    return $x("//p[text()='Invalid username or password!']").as(
        "сообщение 'Не правильный логин или пароль!'");
  }

}

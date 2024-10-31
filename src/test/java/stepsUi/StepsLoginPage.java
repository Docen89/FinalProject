package stepsUi;

import pageObjects.LoginPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class StepsLogin {
  LoginPage lgpage = new LoginPage();

  @Step("Открытие страницы Book Store")
  public  void openBookStore(){
    Selenide.open("https://demoqa.com/login");
  }


  @Step("Вводим имя пользователя")
  public void inputUserName(String nameValue){
    lgpage.userName(nameValue).sendKeys("");
  }

  @Step("Вводим пароль пользователя")
  public void inputPasswordUser(String passwordValue){
    lgpage.userPassword(passwordValue).sendKeys("");
  }

  @Step("Кликаем по кнопке Login")
  public void clickBottonLogin(){
    lgpage.loginButton().click();
  }

  @Step("Кликаем по кнопке New User")
  public void  clickBottonNewUser(){
    lgpage.newUserButton().click();
  }

}

package pageObjects;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class CreateUserPage {

  public SelenideElement firstName(String firstNameValue) {
    return $x("//input[@id='firstname']");
  }

  public SelenideElement lastName(String lastNameValue) {

    return $x("//input[@id='lastname']");
  }

  public SelenideElement userName(String newUserNameValue) {
    return $x("//input[@id='userName']");
  }

  public SelenideElement Password(String newUserPasswordValue) {
    return $x("//input[@id='password']");
  }

  public SelenideElement buttonBackToLogin() {
    return $x("//button[@id='gotologin']");
  }


  public SelenideElement buttonRegister() {
    return $x("//button[@id='register']");
  }

  public  SelenideElement messageCreateUser(String createUserMessageValue){
    return  $x("//p[text()='User exists!']");
  }

  public  SelenideElement messageVerifyCaptcha(String verifyMessageCaptchyValue){
    return $x("//p[text()='Please verify reCaptcha to register!']");
  }

  public SelenideElement noFirstName(){
    return $x("//*[@id='firstname' and @class='mr-sm-2 is-invalid form-control']");
  }

  public SelenideElement noLastName(){
    return  $x("//*[@id='lastname' and @class='mr-sm-2 is-invalid form-control']");
  }

}

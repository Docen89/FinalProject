package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.fasterxml.jackson.core.JsonProcessingException;
import steps.api.StepsAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;


public class TestUi {

  StepsLoginPage slp = new StepsLoginPage();
  StepsProfilePage spp = new StepsProfilePage();
  StepsBookStorePage sbsp = new StepsBookStorePage();
  StepsCreateUserPage scup = new StepsCreateUserPage();
  StepsAccount stAp = new StepsAccount();


  @Test
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("docen");
    slp.inputPasswordUser("docen13");
    slp.clickButtonLogin();
    assertEquals("Invalid username or password!", slp.checkMessagePage());
    slp.clear();
  }


  @Test
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() {
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("Docen89");
    slp.inputPasswordUser("Docen1313!");
    slp.clickButtonLogin();
    slp.checkButtonLogOut();
    assertEquals("Log out", slp.getButtonLogOutTextValue());
    slp.clear();
  }

  @Test
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtomGoToBookStore() {
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("Docen89");
    slp.inputPasswordUser("Docen1313!");
    slp.clickButtonLogin();
    assertEquals("Publisher", sbsp.checkPublisherValue());
    slp.clear();
  }


  @Test
  @DisplayName("Регистрация нового пользователя")
  public void registerNewUserNotCaptcha() {
    slp.openBookStore("https://demoqa.com/login");
    slp.clickButtonNewUser();
    scup.inputNewUserFirstName("Docen90");
    scup.inputNewUserLastName("Popov");
    scup.inputNewUserName("Docen90");
    scup.inputNewUserPassword("Docen1313!");
    scup.clickButtonRegister();
    assertEquals("Please verify reCaptcha to register!", scup.verifyCaptchaMessage());
    slp.clear();
  }

  @Test
  @DisplayName("Не заполнено поле LastName")
  public void noValidLastName() {
    slp.openBookStore("https://demoqa.com/login");
    slp.clickButtonNewUser();
    scup.inputNewUserFirstName("Docen90");
    scup.inputNewUserName("Docen90");
    scup.inputNewUserPassword("Docen1313!");
    scup.clickButtonRegister();
    scup.validLastName();
    slp.clear();
  }

  @Test
  @DisplayName("Не заполнено поле FirstName")
  public void noValidFirstName() {
    slp.openBookStore("https://demoqa.com/login");
    slp.clickButtonNewUser();
    scup.inputNewUserLastName("Popov");
    scup.inputNewUserName("Docen90");
    scup.inputNewUserPassword("Docen1313!");
    scup.clickButtonRegister();
    scup.validFirstName();
    slp.clear();
  }

  @Test
  @DisplayName("Удаление аккаунта пользователя")
  public void deleteUserAccount() throws JsonProcessingException {
    stAp.createNewAccount();
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("Docen90");
    slp.inputPasswordUser("Docen1313!");
    slp.clickButtonLogin();
    spp.clickButtonDeleteAccount();
    spp.acceptAlertDelUser();
    spp.messageDeleteUser();
    assertEquals("User Deleted.", spp.getMessageDeleteUserValue());
    slp.clear();
  }

  @Test
  @DisplayName("Регистрация нового пользователя")
  public void registerNewUser() {
    slp.openBookStore("https://demoqa.com/login");
    slp.clickButtonNewUser();
    scup.inputNewUserFirstName("Docen90");
    scup.inputNewUserLastName("Popov");
    scup.inputNewUserName("Docen90");
    scup.inputNewUserPassword("Docen1313!");
    scup.recaptchaTestClick();
    scup.clickButtonRegister();
    assertEquals("User exists!", scup.userCreateMessage());
    slp.clear();

  }


}

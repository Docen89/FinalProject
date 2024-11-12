package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.api.StepsAccount;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;


public class TestUi extends test.BaseTest {

  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();
  StepsAccount stepsAccount = new StepsAccount();


  @Test
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName("docen");
    stepsLoginPage.inputPasswordUser("docen13");
    stepsLoginPage.clickButtonLogin();
    assertEquals("Invalid username or password!", stepsLoginPage.checkMessagePage());
    stepsLoginPage.clear();

  }


  @Test
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() throws JsonProcessingException {
    stepsAccount.createNewAccount();
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(cfg.newUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.newPasswordValue());
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkButtonLogOut();
    assertEquals("Log out", stepsLoginPage.getButtonLogOutTextValue());
    stepsLoginPage.clear();
    stepsAccount.deleteNewUser();

  }

  @Test
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtomGoToBookStore() throws JsonProcessingException {
    stepsAccount.createNewAccount();
    stepsAccount.getToken();
    stepsProfilePage.getCookieOpenSite("profile", cfg.oldUserNameValue());
    assertEquals("Publisher", stepsBookStorePage.checkPublisherValue());
    stepsAccount.deleteNewUser();
    stepsLoginPage.clear();

  }

  @Test
  @DisplayName("Не заполнено поле LastName")
  public void noValidLastName() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserFirstName("Docen90");
    stepsCreateUserPage.inputNewUserName("Docen90");
    stepsCreateUserPage.inputNewUserPassword("Docen1313!");
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validLastName();
    stepsLoginPage.clear();

  }

  @Test
  @DisplayName("Не заполнено поле FirstName")
  public void noValidFirstName() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserLastName("Popov");
    stepsCreateUserPage.inputNewUserName("Docen90");
    stepsCreateUserPage.inputNewUserPassword("Docen1313!");
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validFirstName();
    stepsLoginPage.clear();

  }

  @Test
  @DisplayName("Удаление аккаунта пользователя")
  public void deleteUserAccount() throws JsonProcessingException {
    stepsAccount.createNewAccount();
    stepsAccount.getToken();
    stepsProfilePage.getCookieOpenSite("profile", cfg.newUserNameValue());
    stepsProfilePage.clickButtonDeleteAccount();
    stepsProfilePage.acceptAlertDelUser();
    stepsProfilePage.messageDeleteUser();
    assertEquals("User Deleted.", stepsProfilePage.getMessageDeleteUserValue());
    stepsLoginPage.clear();

  }
}

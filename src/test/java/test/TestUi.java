package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;


public class TestUi extends test.BaseTest {

  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();
  steps.api.StepsApi stepsApi = new steps.api.StepsApi();


  @Test
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName("docen");
    stepsLoginPage.inputPasswordUser("docen13");
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkMessagePage();
    stepsLoginPage.clear();

  }


  @Test
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() throws JsonProcessingException {
    stepsApi.createNewAccount();
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(cfg.newUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.newPasswordValue());
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkButtonLogOut();
    stepsLoginPage.clear();
    stepsApi.deleteNewUser();

  }

  @Test
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtomGoToBookStore() throws JsonProcessingException {
    stepsApi.createNewAccount();
    stepsApi.getToken();
    stepsProfilePage.getCookieOpenSite("profile", cfg.oldUserNameValue());
    stepsBookStorePage.checkPublisherValue();
    stepsApi.deleteNewUser();
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
    stepsApi.createNewAccount();
    stepsApi.getToken();
    stepsProfilePage.getCookieOpenSite("profile", cfg.newUserNameValue());
    stepsProfilePage.clickButtonDeleteAccount();
    stepsProfilePage.acceptAlertDelUser();
    stepsProfilePage.messageDeleteUser();
    stepsLoginPage.clear();

  }
}

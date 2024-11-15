package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import template.generationdata.GenerationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;
import steps.api.StepsApi;


public class TestUi extends test.BaseTest {

  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();
  StepsApi stepsApi = new StepsApi();
  GenerationDate generationDate = new GenerationDate();


  @Test
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(cfg.newUserNameValue());
    stepsLoginPage.inputPasswordUser(generationDate.newPasswordValue);
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
    stepsCreateUserPage.inputNewUserFirstName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.oldPasswordValue());
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validLastName();
    stepsLoginPage.clear();

  }

  @Test
  @DisplayName("Не заполнено поле FirstName")
  public void noValidFirstName() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserLastName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.newPasswordValue());
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

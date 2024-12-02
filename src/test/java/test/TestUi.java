package test;

import static api.check.VerificationProcedures.statusCode;

import io.qameta.allure.Feature;
import steps.api.UpperStepsApi;
import template.generationdata.GenerationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;
import  steps.api.LowerStepsApi;



public class TestUi extends test.BaseTest {

  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();
  LowerStepsApi lowerStepsApi = new LowerStepsApi();
  GenerationDate generationDate = new GenerationDate();
  UpperStepsApi upperStepsApi =  new UpperStepsApi();


  @Test
  @Feature("Авторизация")
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
  @Feature("Авторизация")
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() {
    lowerStepsApi.createNewAccount();
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(cfg.newUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.newPasswordValue());
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkButtonLogOut();
    stepsLoginPage.clear();
    upperStepsApi.getUserId(cfg.newPasswordValue(),cfg.newUserNameValue());
    upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtomGoToBookStore() {
    lowerStepsApi.createNewAccount()
        .shouldHave(statusCode(201));
    lowerStepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue());
    stepsProfilePage.getCookieOpenSite("profile",cfg.newPasswordValue(), cfg.newUserNameValue());
    stepsBookStorePage.checkPublisherValue();
    upperStepsApi.getUserId(cfg.newPasswordValue(), cfg.newUserNameValue());
    upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));
    stepsLoginPage.clear();

  }

  @Test
  @Feature("Создание пользователя")
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
  @Feature("Создание пользователя")
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
  @Feature("Действия над пользователем")
  @DisplayName("Удаление аккаунта пользователя")
  public void deleteUserAccount()  {
    lowerStepsApi.createNewAccount()
        .shouldHave(statusCode(201));
    lowerStepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue());
    stepsProfilePage.getCookieOpenSite("profile",cfg.newPasswordValue(), cfg.newUserNameValue());
    stepsProfilePage.clickButtonDeleteAccount();
    stepsProfilePage.acceptAlertDelUser();
    stepsProfilePage.messageDeleteUser();
    stepsLoginPage.clear();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Отображение добавленной книги в профиле у пользователя")
  public void viewBookProfileUser()  {
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue())
        .shouldHave(statusCode(201));
    stepsProfilePage.getCookieOpenSite("profile",cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsProfilePage.checkAuthorValue();
    upperStepsApi.deleteBookProfileUser();
    stepsLoginPage.clear();

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Delete All Books")
  public void checkButtonDeleteAllBooks(){
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue())
        .shouldHave(statusCode(201));
    stepsProfilePage.getCookieOpenSite("profile",cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsProfilePage.clickButtonDeleteAllBooks();
    stepsProfilePage.acceptAlertDelAllBooks();
    stepsProfilePage.messageDeletAllBooks();
    stepsLoginPage.clear();

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка отображения информации о книге в профиле пользователя")
  public void checkInfoBookProfileUser(){
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue())
        .shouldHave(statusCode(201));
    stepsProfilePage.getCookieOpenSite("profile",cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsProfilePage.clickToBooProfileUser();
    stepsProfilePage.getDescriptionBook();
    upperStepsApi.deleteBookProfileUser();
    stepsLoginPage.clear();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Поиск книги по названию в BookStore")
  public void searchBookToBookStore(){
    stepsProfilePage.getCookieOpenSite("books",cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsBookStorePage.getValueNameBook();
    stepsBookStorePage.inputSearchBooks();
    stepsBookStorePage.checkTitleBookStore();
    stepsLoginPage.clear();
  }
}

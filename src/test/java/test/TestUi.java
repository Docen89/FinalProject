package test;


import static configs.EndPoints.BOOKS;
import static configs.EndPoints.LOGIN;
import static configs.EndPoints.PROFILE;
import static steps.ui.LowerStepsUI.expiresCookieValueNew;
import static steps.ui.LowerStepsUI.tokenCookieValueNew;
import static steps.ui.LowerStepsUI.userIDCookieValueNew;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import steps.ui.LowerStepsUI;
import steps.api.UpperStepsApi;
import template.generationdata.GenerationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;

@Owner("T. Popov")
@Epic("UI")

public class TestUi extends test.BaseTest {

  LowerStepsUI lowerStepsUI = new LowerStepsUI();
  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();
  GenerationDate generationDate = new GenerationDate();
  UpperStepsApi upperStepsApi = new UpperStepsApi();


  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(generationDate.newUserNameValue());
    stepsLoginPage.inputPasswordUser(generationDate.newPasswordValue());
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkMessagePage();
  }


  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.inputUserName(cfg.oldUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.oldPasswordValue());
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkButtonLogOut();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtonGoToBookStore() {
    lowerStepsUI.openSiteWithCookie("profile", userIDCookieValueOld, tokenCookieValueOld,
        expiresCookieValueOld);
    stepsBookStorePage.checkPublisherValue();
  }

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Не заполнено поле LastName")
  public void noValidLastName() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserFirstName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.oldPasswordValue());
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validLastName();
  }

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Не заполнено поле FirstName")
  public void noValidFirstName() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserLastName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.newPasswordValue());
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validFirstName();
  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление аккаунта пользователя")
  public void deleteUserAccount() {
    lowerStepsUI.creatNewUser();
    lowerStepsUI.addCookieNewUser();
    lowerStepsUI.openSiteWithCookie(PROFILE, userIDCookieValueNew, tokenCookieValueNew,
        expiresCookieValueNew);
    stepsProfilePage.clickButtonDeleteAccount();
    stepsProfilePage.acceptAlertDelUser();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Отображение добавленной книги в профиле у пользователя")
  public void viewBookProfileUser() {
    lowerStepsUI.addBookUser();
    lowerStepsUI.openSiteWithCookie(PROFILE, userIDCookieValueOld, tokenCookieValueOld,
        expiresCookieValueOld);
    stepsProfilePage.checkAuthorValue();
    lowerStepsUI.deleteBookUser();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Delete All Books")
  public void checkButtonDeleteAllBooks() {
    lowerStepsUI.addBookUser();
    lowerStepsUI.openSiteWithCookie(PROFILE, userIDCookieValueOld, tokenCookieValueOld,
        expiresCookieValueOld);
    stepsProfilePage.clickButtonDeleteAllBooks();
    stepsProfilePage.acceptAlertDelAllBooks();
    stepsProfilePage.messageDeleteAllBooks();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка отображения информации о книге в профиле пользователя")
  public void checkInfoBookProfileUser() {
    lowerStepsUI.addBookUser();
    lowerStepsUI.openSiteWithCookie(PROFILE, userIDCookieValueOld, tokenCookieValueOld,
        expiresCookieValueOld);
    stepsProfilePage.clickToBooProfileUser();
    stepsProfilePage.getDescriptionBook();
    upperStepsApi.deleteBookProfileUser();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Поиск книги по названию в BookStore")
  public void searchBookToBookStore() {
    lowerStepsUI.openSiteWithCookie(BOOKS, userIDCookieValueOld, tokenCookieValueOld,
        expiresCookieValueOld);
    stepsBookStorePage.inputSearchBooks();
    stepsBookStorePage.checkTitleBookStore();
  }

}

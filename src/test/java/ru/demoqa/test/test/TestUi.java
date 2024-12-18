package test;


import static ru.demoqa.test.configs.EndPoints.BOOKS;
import static ru.demoqa.test.configs.EndPoints.PROFILE;

import ru.demoqa.test.test.BaseTest;
import steps.ui.HelpersStepsUI;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ui.StepsBookStorePage;
import steps.ui.StepsCreateUserPage;
import steps.ui.StepsLoginPage;
import steps.ui.StepsProfilePage;

@Owner("T. Popov")
@Epic("UI")

public class TestUi extends BaseTest {

  HelpersStepsUI helpersStepsUI = new HelpersStepsUI();
  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  StepsProfilePage stepsProfilePage = new StepsProfilePage();
  StepsBookStorePage stepsBookStorePage = new StepsBookStorePage();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();


  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword() {
    helpersStepsUI.dataTestAuthNotValid();
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkMessagePage();
  }


  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass() {
    helpersStepsUI.dataTestAuthValid();
    stepsLoginPage.clickButtonLogin();
    stepsLoginPage.checkButtonLogOut();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtonGoToBookStore() {
    stepsLoginPage.openSiteWithCookieOldUser(PROFILE);
    stepsBookStorePage.checkPublisherValue();
  }

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Не заполнено поле LastName")
  public void noValidLastName() {
    helpersStepsUI.dataTestCreateUserNoLastName();
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validLastName();
  }

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Не заполнено поле FirstName")
  public void noValidFirstName() {
    helpersStepsUI.dataTestCreateUserFirstName();
    stepsCreateUserPage.clickButtonRegister();
    stepsCreateUserPage.validFirstName();
  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление аккаунта пользователя")
  public void deleteUserAccount() {
    helpersStepsUI.dateTestCreateNewUser();
    stepsProfilePage.clickButtonDeleteAccount();
    stepsProfilePage.acceptAlertDelUser();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Отображение добавленной книги в профиле у пользователя")
  public void viewBookProfileUser() {
    helpersStepsUI.dateTestAddBookToUserProfile();
    stepsProfilePage.checkAuthorValue();
    helpersStepsUI.deleteBookUser();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка кнопки 'Delete All Books")
  public void checkButtonDeleteAllBooks() {
    helpersStepsUI.dateTestAddBookToUserProfile();
    stepsProfilePage.clickButtonDeleteAllBooks();
    stepsProfilePage.acceptAlertDelAllBooks();
    stepsProfilePage.messageDeleteAllBooks();
  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Проверка отображения информации о книге в профиле пользователя")
  public void checkInfoBookProfileUser() {
    helpersStepsUI.dateTestAddBookToUserProfile();
    stepsProfilePage.clickToBookProfileUser();
    stepsProfilePage.getDescriptionBook();
    helpersStepsUI.deleteBookUser();
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Поиск книги по названию в BookStore")
  public void searchBookToBookStore() {
    stepsLoginPage.openSiteWithCookieOldUser(BOOKS);
    stepsBookStorePage.inputSearchBooks();
    stepsBookStorePage.checkTitleBookStore();
  }

}

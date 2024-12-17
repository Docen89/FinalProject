package steps.ui;

import static configs.EndPoints.LOGIN;
import static configs.EndPoints.PROFILE;
import static test.BaseTest.cfg;
import io.qameta.allure.Step;
import steps.api.StepsApi;
import steps.ui.StepsLoginPage;
import template.generationdata.GenerationDate;
import steps.ui.StepsCreateUserPage;
public class HelpersStepsUI {

  StepsApi stepsApi = new StepsApi();
  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  GenerationDate generationDate = new GenerationDate();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();



  @Step("Вспомогательные шаги.Создать нового пользователя")
  public void creatNewUser() {
    stepsApi.createNewAccount();
    stepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue());
  }

  @Step("Вспомогательные шаги.Добавить книгу в профиль к пользователю")
  public void addBookUser() {
    stepsApi.getUserIdValue(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    stepsApi.isbnValue();
    stepsApi.addBookProfileUser();
  }

  @Step("Вспомогательные шаги.Удалить книгу у пользователя")
  public void deleteBookUser() {
    stepsApi.deleteBookProfileUser();
  }

  @Step("Подготовка тестовых данных. Авторизация с невалидными значениями LogoPass.Pass not valid")
  public void dataTestAuthNotValid(){
    stepsLoginPage.openBookStore("login");
    stepsLoginPage.inputUserName(generationDate.newUserNameValue());
    stepsLoginPage.inputPasswordUser(generationDate.newPasswordValue());
  }

  @Step("Подготовка тестовых данных. Авторизация с валидными значениями LogoPass")
  public void dataTestAuthValid(){
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.inputUserName(cfg.oldUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.oldPasswordValue());
  }

  @Step("Подготовка тестовых данных. Заполнение данных о пользотвателе.Не заполнено поле LastName ")
  public void dataTestCreateUserNoLastName(){
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserFirstName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.oldPasswordValue());
  }

  @Step("Подготовка тестовых данных. Заполнение данных о пользотвателе.Не заполнено поле FirstName ")
  public void dataTestCreateUserFirstName(){
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserLastName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.newUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.newPasswordValue());
  }
  @Step("Подготовка тестовых данных.Создание нового пользователя")
  public void dateTestCreateNewUser(){
    creatNewUser();
    stepsLoginPage.openSiteWithCookieNewUser(PROFILE);
  }

  @Step("Подготовка тестовых данных.Добавление книги в профиль пользователю")
  public void dateTestAddBookToUserProfile(){
    addBookUser();
    stepsLoginPage.openSiteWithCookieOldUser(PROFILE);
  }

}

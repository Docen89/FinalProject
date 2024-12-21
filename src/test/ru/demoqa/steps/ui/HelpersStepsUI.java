package ru.demoqa.steps.ui;


import static ru.demoqa.configs.EndPoints.LOGIN;
import static ru.demoqa.configs.EndPoints.PROFILE;
import static ru.demoqa.test.BaseTest.cfg;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.demoqa.generationdata.GenerationDate;
import ru.demoqa.helpers.GuiUserCookie;
import ru.demoqa.steps.api.StepsApi;


public class HelpersStepsUI {

  StepsApi stepsApi = new StepsApi();
  StepsLoginPage stepsLoginPage = new StepsLoginPage();
  GenerationDate generationDate = new GenerationDate();
  StepsCreateUserPage stepsCreateUserPage = new StepsCreateUserPage();

  @Step("Очистить кэш")
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }

  @Step("Вспомогательные шаги.Создать нового пользователя")
  public void createNewUser(String password, String userName) {
    stepsApi.createNewAccount(password, userName, userName, password);
    stepsApi.getToken(password, userName);
    stepsApi.authUser(password, userName);
  }

  @Step("Вспомогательные шаги.Добавить книгу в профиль к пользователю")
  public void addBookUser() {
    stepsApi.addBookProfileUser(
        stepsApi.getLoginUserId(cfg.guiNewPasswordValue(), cfg.guiNewUserNameValue()),
        cfg.guiNewUserNameValue(), cfg.guiNewPasswordValue());
  }

  @Step("Вспомогательные шаги.Удалить книгу у пользователя")
  public void deleteBookUser() {
    stepsApi.deleteBookProfileUser();
  }

  @Step("Подготовка тестовых данных. Авторизация с невалидными значениями LogoPass.Pass not valid")
  public void dataTestAuthNotValid() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.inputUserName(generationDate.newUserNameValue());
    stepsLoginPage.inputPasswordUser(generationDate.newPasswordValue());
  }

  @Step("Подготовка тестовых данных. Авторизация с валидными значениями LogoPass")
  public void dataTestAuthValid() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.inputUserName(cfg.oldUserNameValue());
    stepsLoginPage.inputPasswordUser(cfg.oldPasswordValue());
  }

  @Step("Подготовка тестовых данных. Заполнение данных о пользотвателе.Не заполнено поле LastName ")
  public void dataTestCreateUserNoLastName() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserFirstName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.oldUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.oldPasswordValue());
  }

  @Step("Подготовка тестовых данных. Заполнение данных о пользотвателе.Не заполнено поле FirstName ")
  public void dataTestCreateUserFirstName() {
    stepsLoginPage.openBookStore(LOGIN);
    stepsLoginPage.clickButtonNewUser();
    stepsCreateUserPage.inputNewUserLastName(cfg.killUserNameValue());
    stepsCreateUserPage.inputNewUserName(cfg.killUserNameValue());
    stepsCreateUserPage.inputNewUserPassword(cfg.killPasswordValue());
  }

  @Step("Подготовка тестовых данных.Добавление книги в профиль пользователю")
  public void dateTestAddBookToUserProfile() {
    addBookUser();
    stepsLoginPage.openSiteWithCookieUser(PROFILE,
        GuiUserCookie.getInstance().getGuiUserIdValueNewUser(),
        GuiUserCookie.getInstance().getGuiTokenValueNewUser(),
        GuiUserCookie.getInstance().getGuiExpiresValueNewUser());
  }

}


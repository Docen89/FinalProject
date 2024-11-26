package steps.ui;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.BaseTest.cfg;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import template.request.createUserBody.CreateUserBodyRequest;
import steps.api.LowerStepsApi;
import model.response.Auth.ResponseAuthBody;
import api.ActionsResponce;

public class StepsProfilePage {

  String messageDeleteUserValue;
  ProfilePage profilePage = new ProfilePage();
  LowerStepsApi lowerStepsApi = new LowerStepsApi();
  ResponseAuthBody responseAuthBody = new ResponseAuthBody();


  public void clickButtonGoToTheBookStore() {
    profilePage.buttonGoToBookStore().click();
  }

  public void clickButtonDeleteAccount() {
    profilePage.buttonDeleteAccount().scrollTo().click();
  }

  public void clickButtonDeleteAllBooks() {
    profilePage.buttonDeleteAllBooks().click();
  }

  public void inputSearchBooks(String bookNameValue) {
    profilePage.searchBook(bookNameValue).sendKeys(bookNameValue);
  }

  @Step("Подтвердить удаление аккаунта")
  public void acceptAlertDelUser() {
    profilePage.buttonMessageDeleteUserOk().click();
  }


  @Step("Получить уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
    assertEquals("User Deleted.", messageDeleteUserValue);
  }

  @Step("Кликнуть по кнопке 'Log out'")
  public void clickButtonLogOut() {
    profilePage.buttonLogOut().click();
  }

  @Step("Получить Cookie")
  public void getCookieOpenSite(String endPoint,String password,String userName) {
    ActionsResponce responce= lowerStepsApi
        .authorization(password, userName);
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("userID", responce.getBodyFieldString("userId"))));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("token", responce.getBodyFieldString("token"))));
    WebDriverRunner.getWebDriver().manage()
        .addCookie((new Cookie("expires", responce.getBodyFieldString("expires"))));
    open(endPoint);
  }

}

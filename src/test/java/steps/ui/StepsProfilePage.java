package steps.ui;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import steps.api.LowerStepsApi;
import api.ActionsResponce;


public class StepsProfilePage {
  String descriptionValue;
  String messageDeleteAllBooks;
  String authorValue;
  String messageDeleteUserValue;
  ProfilePage profilePage = new ProfilePage();
  LowerStepsApi lowerStepsApi = new LowerStepsApi();


  public void clickButtonGoToTheBookStore() {
    profilePage.buttonGoToBookStore().click();
  }

  public void clickButtonDeleteAccount() {
    profilePage.buttonDeleteAccount().scrollTo().click();
  }

  public void clickButtonDeleteAllBooks() {
    profilePage.buttonDeleteAllBooks().scrollTo().click();
  }



  @Step("Подтвердить удаление аккаунта")
  public void acceptAlertDelUser() {
    profilePage.buttonMessageDeleteUserOk().click();
  }

  @Step("Подтвердить удаление всех книг")
  public void acceptAlertDelAllBooks() {
    profilePage.buttonMessageDeleteAllBooks().click();
  }


  @Step("Получить уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
    assertEquals("User Deleted.", messageDeleteUserValue);
  }

  @Step("Получить уведомление об удалении всех книг")
  public void messageDeletAllBooks() {
    messageDeleteAllBooks = Selenide.switchTo().alert().getText();
    assertEquals("All Books deleted.", messageDeleteAllBooks);
  }

  @Step("Кликнуть по кнопке 'Log out'")
  public void clickButtonLogOut() {
    profilePage.buttonLogOut().click();
  }

  @Step("Получить Cookie")
  public void getCookieOpenSite(String endPoint, String password, String userName) {
    ActionsResponce responce = lowerStepsApi
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


  @Step("Проверить наличие колонки 'Автор'")
  public void checkAuthorValue() {
    authorValue = profilePage.checkAddBookProfile().getText();
    assertEquals("Glenn Block et al.", authorValue);
  }

  @Step("Кликнуть по книге в профиле пользователя")
  public void clickToBooProfileUser(){
    profilePage.infoBook().click();
  }

  @Step("Получить описание книги")
  public void getDescriptionBook(){
   descriptionValue= profilePage.descriptionBook().getText();
   assertEquals("Description :",descriptionValue);
  }


}


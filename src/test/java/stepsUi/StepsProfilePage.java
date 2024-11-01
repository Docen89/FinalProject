package stepsUi;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;
import pageObjects.ProfilePage;

public class StepsProfilePage {

  @Getter
  String messageDeleteUserValue;

  ProfilePage pfpage = new ProfilePage();

  public void clickButtomGoToTheBookStore() {
    pfpage.bottonGoToBookStore().click();
  }

  public void clickButtomDeleteAccount() {
    pfpage.bottonDeleteAccount().scrollTo().click();
  }

  public void clickButtomDeleteAllBooks() {
    pfpage.bottonDeleteAllBooks().click();
  }

  public void inputSearchBooks(String bookNameValue) {
    pfpage.searchBook(bookNameValue).sendKeys(bookNameValue);
  }

  @Step("Потверждаем удаление акккаунта")
  public void acceptAlertDelUser() {
    pfpage.buttonMessageDeleteUserOk().click();
  }


  @Step("Получаем уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
  }

  @Step("Кликаем по кнопке 'Log out'")
  public void clickBottonLogOut() {
    pfpage.bottonLogOut().click();
  }
}

package steps.ui;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.Getter;
import page.ProfilePage;

public class StepsProfilePage {

  @Getter
  String messageDeleteUserValue;

  ProfilePage pfpage = new ProfilePage();

  public void clickButtonGoToTheBookStore() {
    pfpage.buttonGoToBookStore().click();
  }

  public void clickButtonDeleteAccount() {
    pfpage.buttonDeleteAccount().scrollTo().click();
  }

  public void clickButtonDeleteAllBooks() {
    pfpage.buttonDeleteAllBooks().click();
  }

  public void inputSearchBooks(String bookNameValue) {
    pfpage.searchBook(bookNameValue).sendKeys(bookNameValue);
  }

  @Step("Подтверждаем удаление аккаунта")
  public void acceptAlertDelUser() {
    pfpage.buttonMessageDeleteUserOk().click();
  }


  @Step("Получаем уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
  }

  @Step("Кликаем по кнопке 'Log out'")
  public void clickButtonLogOut() {
    pfpage.buttonLogOut().click();
  }
}

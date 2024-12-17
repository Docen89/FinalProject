package steps.ui;


import static com.codeborne.selenide.Condition.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import page.ProfilePage;


public class StepsProfilePage {

  ProfilePage profilePage = new ProfilePage();

  @Step("Клик по кнопке 'Удалить аккаунт'")
  public void clickButtonDeleteAccount() {
    profilePage.buttonDeleteAccount().scrollTo().click();
  }

  @Step("Клик по кнопке 'Удалить все книги'")
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
    String messageDeleteUserValue;
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
    assertEquals("User Deleted.", messageDeleteUserValue);
  }

  @Step("Получить уведомление об удалении всех книг")
  public void messageDeleteAllBooks() {
    String messageDeleteAllBooks;
    messageDeleteAllBooks = Selenide.switchTo().alert().getText();
    assertEquals("All Books deleted.", messageDeleteAllBooks);
  }

  @Step("Проверить наличие колонки 'Автор'")
  public void checkAuthorValue() {
  profilePage.checkAddBookProfile().shouldHave(text("Richard E. Silverman"));
  }

  @Step("Клик по книге в профиле пользователя")
  public void clickToBookProfileUser() {
    profilePage.infoBook().scrollTo().click();
  }

  @Step("Получить описание книги")
  public void getDescriptionBook() {
    profilePage.descriptionBook().shouldHave(text("Description"));
  }

}


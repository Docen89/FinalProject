package ru.demoqa.steps.ui;


import static com.codeborne.selenide.Condition.text;

import io.qameta.allure.Step;
import ru.demoqa.page.ProfilePage;


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

  @Step("Потвердить удаление книги")
  public void acceptDeleteBook() {
    profilePage.buttonMessageDeleteBookOk().click();
  }

  @Step("Удалить книгу")
  public void deleteBook() {
    profilePage.buttonDeleteBook().scrollTo().click();
  }

  @Step("Подтвердить удаление всех книг")
  public void acceptAlertDelAllBooks() {
    profilePage.buttonMessageDeleteAllBooks().click();
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


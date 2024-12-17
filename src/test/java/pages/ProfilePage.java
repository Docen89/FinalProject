package page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class ProfilePage {


  public SelenideElement buttonDeleteAccount() {
    return $x("//button[text()='Delete Account']").as("кнопка 'Удалить аккаунт");
  }

  public SelenideElement buttonDeleteAllBooks() {
    return $x("//button[text()='Delete All Books']").as("кнопка 'Удалить все книги'");
  }

  public SelenideElement buttonLogOut() {
    return $x("//button[text()='Log out']").as("кнопка 'Выйти из личного кабинета'");
  }

  public SelenideElement buttonMessageDeleteUserOk() {
    return $x("//*[@id='closeSmallModal-ok']").as("Аллерт - сообщение об удаление пользователя");
  }

  public SelenideElement checkAddBookProfile() {
    return $x("//div[text()='Richard E. Silverman']").as(
        "Отображение колонки 'Автор' у книги в профиле пользователя");
  }

  public SelenideElement buttonMessageDeleteAllBooks() {
    return $x("//*[@id='closeSmallModal-ok']").as("Аллерт - сообщение об удалении всех книг");
  }

  public SelenideElement infoBook() {
    return $x("//*[@id='see-book-Git Pocket Guide']/a").as("Информация о книге");
  }

  public SelenideElement descriptionBook() {
    return $x(" //*[@id='description-label']").as("Описание книги");
  }

}



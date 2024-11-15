package page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class ProfilePage {

  public SelenideElement searchBook(String bookName) {
    return $x("//input[@id='searchBox']").as("строка поиска");
  }

  public SelenideElement buttonGoToBookStore() {
    return $x("//button[text()='Go To Book Store']").as("кнопка 'Перейти в Магазин книг'");
  }

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

}



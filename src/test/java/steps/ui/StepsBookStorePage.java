package steps.ui;

import static com.codeborne.selenide.Condition.text;
import static steps.ui.LowerStepsUI.stepsApi;

import io.qameta.allure.Step;
import page.BookStorePage;



public class StepsBookStorePage {

  BookStorePage bookStorePage = new BookStorePage();

  @Step("Проверить наличие колонки 'Автор'")
  public void checkPublisherValue() {
      bookStorePage.publisher().shouldHave(text("Publisher"));
  }

  @Step("Запросить название книги")
  public String nameBookValue() {
    String nameBookValue;
    api.ActionsResponce responce = stepsApi.getNameBook();
    nameBookValue = responce.getBodyFieldString("books[0].title");
    return nameBookValue;
  }

  @Step("Получить название книги")
  public void inputSearchBooks() {
    bookStorePage.searchBook().setValue(nameBookValue());
  }

  @Step("Проверить автора")
  public void checkTitleBookStore() {
    bookStorePage.titleBookStore().shouldHave(text("Git Pocket Guide"));
  }

}

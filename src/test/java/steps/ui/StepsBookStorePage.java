package steps.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import steps.api.UpperStepsApi;
import page.BookStorePage;
import api.ActionsResponce;

public class StepsBookStorePage {

  UpperStepsApi upperStepsApi = new UpperStepsApi();
  BookStorePage bookStorePage = new BookStorePage();

  @Step("Проверить наличие колонки 'Автор'")
  public void checkPublisherValue() {
    String publisherValue;
    publisherValue = bookStorePage.publisher().getText();
    assertEquals("Publisher", publisherValue);
  }

  @Step("Запросить название книги")
  public String nameBookValue() {
    String nameBookValue;
    ActionsResponce responce = upperStepsApi.getNameBook();
    nameBookValue = responce.getBodyFieldString("books[0].title");
    return nameBookValue;
  }

  @Step("Получить название книги")
  public void inputSearchBooks() {
    bookStorePage.searchBook().sendKeys(nameBookValue());
  }

  @Step("Проверить автора")
  public void checkTitleBookStore() {
    String nameBookValueCheck;
    nameBookValueCheck = bookStorePage.titleBookStore().getText();
    assertEquals("Git Pocket Guide", nameBookValueCheck);
  }

}

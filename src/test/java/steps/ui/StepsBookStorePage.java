package steps.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import  steps.api.UpperStepsApi;
import page.BookStorePage;

public class StepsBookStorePage {
  UpperStepsApi upperStepsApi = new UpperStepsApi();
  String nameTitleValue;

  private  String publisherValue;
  BookStorePage bookStorePage = new BookStorePage();

  @Step("Проверить наличие колонки 'Автор'")
  public void checkPublisherValue() {
    publisherValue = bookStorePage.publisher().getText();
    assertEquals("Publisher", publisherValue);
  }

  @Step("Получить название книги")
  public void getValueNameBook(){
    api.ActionsResponce responce = upperStepsApi.getNameBook();
    nameTitleValue =responce.getBodyFieldString("title");

  }

  public void inputSearchBooks() {
    bookStorePage.searchBook().sendKeys(nameTitleValue);
  }
  public void checkTitleBookStore(){
    nameTitleValue= bookStorePage.titleBookStore().getText();
    assertEquals("Designing Evolvable Web APIs with ASP.NET",nameTitleValue);
  }
}

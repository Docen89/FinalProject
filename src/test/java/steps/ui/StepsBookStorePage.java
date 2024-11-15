package steps.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import lombok.Getter;
import page.BookStorePage;

public class StepsBookStorePage {

  @Getter
  String publisherValue;
  BookStorePage bookStorePage = new BookStorePage();

  @Step("Проверить наличие колонки 'Автор'")
  public void checkPublisherValue() {
    publisherValue = bookStorePage.publisher().getText();
    assertEquals("Publisher", publisherValue);
  }
}

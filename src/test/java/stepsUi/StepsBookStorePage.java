package stepsUi;

import io.qameta.allure.Step;
import lombok.Getter;
import pageObjects.BookStorePage;

public class StepsBookStorePage {
  @Getter
  String publisherValue;
  BookStorePage bsp = new BookStorePage();

  @Step("Проверяем наличие колонки 'Автор'")
  public String  checkPublisherValue(){
    publisherValue = bsp.publisher().getText();
    return publisherValue;
  }
}

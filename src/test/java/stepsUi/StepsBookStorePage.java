package stepsUi;

import io.qameta.allure.Step;
import lombok.Getter;

public class BookStorePage {
  @Getter
  String checkPublisherValue;
  BookStorePage bsp = new BookStorePage();

  @Step("Проверяем наличие колонки 'Автор'")
  public void 
}

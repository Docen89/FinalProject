package page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {

  public SelenideElement publisher() {
    return $x("//div[contains(text(), 'Publisher')]").as("Колонка 'Автор'");
  }

  public SelenideElement searchBook() {
    return $x("//input[@id='searchBox']").as("строка поиска");
  }

  public SelenideElement titleBookStore() {
    return $x("//a[text()='Git Pocket Guide']").as("Колонка 'Название");
  }
}



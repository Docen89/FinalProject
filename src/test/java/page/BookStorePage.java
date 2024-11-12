package page;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {

  public SelenideElement publisher() {
    return $x("//div[contains(text(), 'Publisher')]");
  }
}



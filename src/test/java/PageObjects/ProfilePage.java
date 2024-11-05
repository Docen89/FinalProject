package PageObjects;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class ProfilePage {

  public SelenideElement searchBook(String bookName) {
    return $x("//input[@id='searchBox']");
  }

  public SelenideElement buttonGoToBookStore() {
    return $x("//button[text()='Go To Book Store']");
  }

  public SelenideElement buttonDeleteAccount() {

    return $x("//button[text()='Delete Account']");
  }

  public SelenideElement buttonDeleteAllBooks() {
    return $x("//button[text()='Delete All Books']");
  }

  public SelenideElement buttonLogOut() {
    return $x("//button[text()='Log out']");
  }

  public SelenideElement buttonMessageDeleteUserOk() {
    return $x("//*[@id='closeSmallModal-ok']");
  }

}



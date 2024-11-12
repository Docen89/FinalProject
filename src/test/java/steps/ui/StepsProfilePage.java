package steps.ui;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.Matchers.equalTo;
import static test.BaseTest.cfg;

import lombok.Getter;


public class StepsProfilePage {

  @Getter
  String messageDeleteUserValue;
  java.model.response.Auth.ResponseAuthBody responseAuthBody = new ResponseAuthBody();
  RequestAuthorizedBody requestAuthorizedBody= new RequestAuthorizedBody();
  ProfilePage pfpage = new ProfilePage();

  public void clickButtonGoToTheBookStore() {
    pfpage.buttonGoToBookStore().click();
  }

  public void clickButtonDeleteAccount() {
    pfpage.buttonDeleteAccount().scrollTo().click();
  }

  public void clickButtonDeleteAllBooks() {
    pfpage.buttonDeleteAllBooks().click();
  }

  public void inputSearchBooks(String bookNameValue) {
    pfpage.searchBook(bookNameValue).sendKeys(bookNameValue);
  }

  @Step("Подтверждаем удаление аккаунта")
  public void acceptAlertDelUser() {
    pfpage.buttonMessageDeleteUserOk().click();
  }


  @Step("Получаем уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
  }

  @Step("Кликаем по кнопке 'Log out'")
  public void clickButtonLogOut() {
    pfpage.buttonLogOut().click();
  }

  @Step("Получаем Cookie и открываем страницу")
  public void getCookieOpenSite() throws JsonProcessingException {
    api.RestAssuredHaveBodyRequestNoAuthPost restAssuredHaveBodyRequestNoAuthPost = new api.RestAssuredHaveBodyRequestNoAuthPost()
        .post("https://demoqa.com/Account/v1/Login",
            requestAuthorizedBody.completionRequestAuthorizedBody())
        .responseStatusCode(200)
        .responseJson("username", equalTo(cfg.userNameValue()));
    String jsonResponseAuthBody = restAssuredHaveBodyRequestNoAuthPost.getResponse().body().print();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseAuthBody responseAuthBody = objectMapper.readValue(jsonResponseAuthBody,
        ResponseAuthBody.class);
    open("https://demoqa.com/books");
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("userID", responseAuthBody.getUserId()));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("token", responseAuthBody.getToken()));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("expires", responseAuthBody.getExpires()));
    open("https://demoqa.com/books");
  }
}

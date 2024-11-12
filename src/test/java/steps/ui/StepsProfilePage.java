package steps.ui;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.Matchers.equalTo;
import static test.BaseTest.cfg;

import api.RestAssuredHaveBodyRequestNoAuthPost;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import model.response.Auth.ResponseAuthBody;
import lombok.Getter;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import template.completion.request.authorizedBody.RequestAuthorizedBody;

public class StepsProfilePage {

  @Getter
  String messageDeleteUserValue;
  RequestAuthorizedBody requestAuthorizedBody = new RequestAuthorizedBody();
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
  public void getCookieOpenSite(String endPoint, String newUserName)
      throws JsonProcessingException {
    RestAssuredHaveBodyRequestNoAuthPost restAssuredHaveBodyRequestNoAuthPost = new RestAssuredHaveBodyRequestNoAuthPost()
        .post("https://demoqa.com/Account/v1/Login",
            requestAuthorizedBody.completionRequestAuthorizedBody())
        .responseStatusCode(200)
        .responseJson("username", equalTo(newUserName));
    String jsonResponseAuthBody = restAssuredHaveBodyRequestNoAuthPost.getResponse().body().print();
    ObjectMapper objectMapper = new ObjectMapper();
    ResponseAuthBody responseAuthBody = objectMapper.readValue(jsonResponseAuthBody,
        ResponseAuthBody.class);
    open(endPoint);
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("userID", responseAuthBody.getUserId()));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("token", responseAuthBody.getToken()));
    WebDriverRunner.getWebDriver().manage()
        .addCookie(new Cookie("expires", responseAuthBody.getExpires()));
    open(endPoint);
  }
}

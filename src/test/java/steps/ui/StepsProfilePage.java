package steps.ui;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.RestAssuredHaveBodyRequestNoAuthPost;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import model.response.Auth.ResponseAuthBody;
import org.openqa.selenium.Cookie;
import page.ProfilePage;
import template.request.createUserBody.CreateUserBodyRequest;

public class StepsProfilePage {

  String messageDeleteUserValue;
  CreateUserBodyRequest createUserBodyRequest = new CreateUserBodyRequest();
  ProfilePage profilePage = new ProfilePage();


  public void clickButtonGoToTheBookStore() {
    profilePage.buttonGoToBookStore().click();
  }

  public void clickButtonDeleteAccount() {
    profilePage.buttonDeleteAccount().scrollTo().click();
  }

  public void clickButtonDeleteAllBooks() {
    profilePage.buttonDeleteAllBooks().click();
  }

  public void inputSearchBooks(String bookNameValue) {
    profilePage.searchBook(bookNameValue).sendKeys(bookNameValue);
  }

  @Step("Подтвердить удаление аккаунта")
  public void acceptAlertDelUser() {
    profilePage.buttonMessageDeleteUserOk().click();
  }


  @Step("Получить уведомление об удаление пользователя")
  public void messageDeleteUser() {
    messageDeleteUserValue = Selenide.switchTo().alert().getText();
    assertEquals("User Deleted.", messageDeleteUserValue);
  }

  @Step("Кликнуть по кнопке 'Log out'")
  public void clickButtonLogOut() {
    profilePage.buttonLogOut().click();
  }

  @Step("Получить Cookie и открыть страницу")
  public void getCookieOpenSite(String endPoint, String newUserName)
      throws JsonProcessingException {
    RestAssuredHaveBodyRequestNoAuthPost restAssuredHaveBodyRequestNoAuthPost = new RestAssuredHaveBodyRequestNoAuthPost()
        .post("https://demoqa.com/Account/v1/Login",
        createUserBodyRequest.completionRequestCreateUserBody())
        .responseStatusCode(200);
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

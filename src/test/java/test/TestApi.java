package test;



import static api.check.VerificationProcedures.bodyField;
import static api.check.VerificationProcedures.statusCode;
import static org.hamcrest.Matchers.equalTo;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import steps.api.UpperStepsApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  steps.api.LowerStepsApi;
import test.BaseTest;

@Owner("T. Popov")
@Epic("API")

public class TestApi extends BaseTest {

LowerStepsApi lowerStepsApi = new LowerStepsApi();
UpperStepsApi upperStepsApi =  new UpperStepsApi();

  @Test
  @Feature("Создание пользователя")
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    lowerStepsApi.createNewAccount()
        .shouldHave(statusCode(201));
    upperStepsApi.getUserId(cfg.newPasswordValue(), cfg.newUserNameValue());
        upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));
  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authUser()  {
    lowerStepsApi.getToken(cfg.oldPasswordValue(), cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("result",equalTo("User authorized successfully.")));
    lowerStepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("username",equalTo(cfg.oldUserNameValue())));

  }

  @Test
  @Feature("Авторизация")
  @DisplayName("Авторизация незарегистрированным пользователем")
  public void authNotRegisterUser()  {
    lowerStepsApi.getToken(cfg.newPasswordValue(), cfg.newUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("status",equalTo("Failed")))
        .shouldHave(bodyField("result",equalTo("User authorization failed.")));

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser()  {
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue())
        .shouldHave(statusCode(201));
    upperStepsApi.deleteBookProfileUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser()  {
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue());
    upperStepsApi.checkMessageResponseRepeatedAddBooKProfileUser()
        .shouldHave(statusCode(400))
        .shouldHave(bodyField("message",equalTo("ISBN already present in the User's Collection!")));
    upperStepsApi.deleteBookProfileUser();

  }

  @Test
  @Feature("Действия в профиле")
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    upperStepsApi.getUserId(cfg.oldPasswordValue(), cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue());
    upperStepsApi.deleteBookProfileUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @Feature("Действия над пользователем")
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser()  {
    lowerStepsApi.createNewAccount();
    upperStepsApi.getUserId(cfg.newPasswordValue(), cfg.newUserNameValue());
    upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    upperStepsApi.getBook(cfg.realIsbnValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("isbn",equalTo(cfg.realIsbnValue())));
  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    upperStepsApi.getBook(cfg.notRealIsbnValue())
        .shouldHave(statusCode(400))
        .shouldHave(bodyField("message",equalTo("ISBN supplied is not available in Books Collection!")));;

  }

  @Test
  @Feature("Действия над книгами")
  @DisplayName("Проверка сохранения книги в профиле у пользователя")
  public void checkBookProfileUser(){
    upperStepsApi.getUserId(cfg.oldPasswordValue(),cfg.oldUserNameValue());
    upperStepsApi.addBookProfileUser(cfg.realIsbnValue());
    upperStepsApi.getInfoUser(cfg.oldUserNameValue(),cfg.oldPasswordValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("books[0].isbn",equalTo(cfg.realIsbnValue())));
    upperStepsApi.deleteBookProfileUser();

  }

}

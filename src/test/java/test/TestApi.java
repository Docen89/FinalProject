package test;



import static api.check.VerificationProcedures.bodyField;
import static api.check.VerificationProcedures.statusCode;
import static org.hamcrest.Matchers.equalTo;

import steps.api.UpperStepsApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  steps.api.LowerStepsApi;
import test.BaseTest;


public class TestApi extends BaseTest {

LowerStepsApi lowerStepsApi = new LowerStepsApi();
UpperStepsApi upperStepsApi =  new UpperStepsApi();

  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    lowerStepsApi.createNewAccount()
        .shouldHave(statusCode(201));
    upperStepsApi.addUserIdNewUser();
        upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));
  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser()  {
    lowerStepsApi.getToken(cfg.oldPasswordValue(), cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("result",equalTo("User authorized successfully.")));
    lowerStepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue())
        .shouldHave(statusCode(200))
        .shouldHave(bodyField("username",equalTo(cfg.oldUserNameValue())));

  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser()  {
    upperStepsApi.addUserIdOldUser();
    upperStepsApi.addBookProfileUser()
        .shouldHave(statusCode(201));
    upperStepsApi.deleteBookProfileUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser()  {
    upperStepsApi.addUserIdOldUser();
    upperStepsApi.addBookProfileUser();
    upperStepsApi.checkMessageResponseRepeatedAddBooKProfileUser()
        .shouldHave(statusCode(400))
        .shouldHave(bodyField("message",equalTo("ISBN already present in the User's Collection!")));
    upperStepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    upperStepsApi.addUserIdOldUser();
    upperStepsApi.addBookProfileUser();
    upperStepsApi.deleteBookProfileUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser()  {
    lowerStepsApi.createNewAccount();
    upperStepsApi.addUserIdNewUser();
    upperStepsApi.deleteUser()
        .shouldHave(statusCode(204));

  }

  @Test
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    upperStepsApi.getRealBook();
  }

  @Test
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    upperStepsApi.getNoRealBook();

  }

}

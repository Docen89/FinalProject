package test;



import steps.api.UpperStepsApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import  steps.api.LowerStepsApi;


public class TestApi extends test.BaseTest {

LowerStepsApi lowerStepsApi = new LowerStepsApi();
UpperStepsApi upperStepsApi =  new UpperStepsApi();

  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    lowerStepsApi.createNewAccount();
    upperStepsApi.deleteNewUser();

  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser()  {
    lowerStepsApi.authorization();

  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser()  {
    lowerStepsApi.authorization();
    upperStepsApi.addBookProfileUser();
    upperStepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser()  {
    lowerStepsApi.authorization();
    upperStepsApi.addBookProfileUser();
    upperStepsApi.checkMessageResponseRepeatedAddBooKProfileUser();
    upperStepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    lowerStepsApi.authorization();
    upperStepsApi.addBookProfileUser();
    upperStepsApi.deleteBookProfileUser();

  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser()  {
    lowerStepsApi.createNewAccount();
    upperStepsApi.deleteNewUser();

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

package TestApi;


import StepsApi.StepsAccount;
import StepsApi.StepsBook;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestApi {

  StepsAccount stAp = new StepsAccount();
  StepsBook stBk = new StepsBook();

  @Test
  @DisplayName("Создание нового пользователя")
  public void createNewUser() {
    stAp.createNewAccount();
    stAp.checkLoginNewUser();
    stAp.deleteNewUser();
  }

  @Test
  @DisplayName("Авторизация зарегистрированным пользователем")
  public void authOldUser() {
    stAp.authorization();
    stAp.checkStatusResponseAuthOldUser();
  }

  @Test
  @DisplayName("Добавление книги к пользователю в профиль")
  public void addBookToOldUser() {
    stBk.addBookProfileUser();
    stBk.ckeckStatusResponseAddBooKProfileUser();
    stBk.deleteBookProfileUser();
  }

  @Test
  @DisplayName("Повторное добавление книги  в профиль пользователя")
  public void bookIsAlreadyThereUser() {
    stBk.addBookProfileUser();
    stBk.addBookProfileUser();
    stBk.ckeckMessageResponseAddBooKProfileUser();
    stBk.deleteBookProfileUser();
  }

  @Test
  @DisplayName("Удаляем книгу из профиля пользователя")
  public void deleteBookInProfileUser() {
    stBk.addBookProfileUser();
    stBk.deleteBookProfileUser();
    stBk.checkResponseStatusDeleteBookProfile();
  }

  @Test
  @DisplayName("Удаление  пользователя")
  public void deleteNewUser() {
    stAp.createNewAccount();
    stAp.deleteNewUser();
    stAp.checkResponseStatusDeleteUser();
  }

  @Test
  @DisplayName("Запрос определенной книги")
  public void getBooksFromStore() {
    stBk.getRealBook();
    stBk.ckeckTitleRealBook();
  }

  @Test
  @DisplayName("Запрос несуществующей книги")
  public void getNonExistentBookStore() {
    stBk.getNoRealBook();
    stBk.checkMessageResponseNotRealBook();
  }

}

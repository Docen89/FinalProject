
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import stepsUi.StepsBookStorePage;
import stepsUi.StepsLoginPage;
import stepsUi.StepsProfilePage;


public class TestUi {
  StepsLoginPage slp = new StepsLoginPage();
  StepsProfilePage spp = new StepsProfilePage();
  StepsBookStorePage sbsp = new StepsBookStorePage();

  @Test
  @DisplayName("Авторизация  с неверным паролем от пользователя")
  public void authWithNotValidPassword(){
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("docen");
    slp.inputPasswordUser("docen13");
    slp.clickBottonLogin();
    assertEquals("Invalid username or password!",slp.checkMessagePage());
  }


  @Test
  @DisplayName("Авторизация с валидной парой логопасс")
  public void authWithValidLogoPass(){
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("Docen89");
    slp.inputPasswordUser("Docen1313!");
    slp.clickBottonLogin();
    slp.checkButtonLogOut();
    assertEquals("Log out",slp.getButtomLogOutTextValue());
  }

  @Test
  @DisplayName("Проверка кнопки 'Go To Book Store'")
  public void checkButtomGoToBookStore(){
    slp.openBookStore("https://demoqa.com/login");
    slp.inputUserName("Docen89");
    slp.inputPasswordUser("Docen1313!");
    slp.clickBottonLogin();
    assertEquals("Publisher",sbsp.checkPublisherValue());
  }


}

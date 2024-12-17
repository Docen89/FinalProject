package test;

import static com.codeborne.selenide.Configuration.baseUrl;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.config;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import java.util.HashMap;
import java.util.Map;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import api.ActionsResponce;

public class BaseTest {


  static steps.api.StepsApi stepsApi = new steps.api.StepsApi();

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = cfg.browserSize();

  }
  public static Map<String, String> getCookieOldUser(){
      ActionsResponce responceOldUser = stepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue());
      Map<String, String> tokenAndExpiresAndUserIdValue = new HashMap<>();
      tokenAndExpiresAndUserIdValue.put("token", responceOldUser.getBodyFieldString("token"));
      tokenAndExpiresAndUserIdValue.put("expires", responceOldUser.getBodyFieldString("expires"));
      tokenAndExpiresAndUserIdValue.put("userId",responceOldUser.getBodyFieldString("userId"));
      return tokenAndExpiresAndUserIdValue;
  }

  @Step("Чистим кэш")
  @AfterEach
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }
}
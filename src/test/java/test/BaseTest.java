package test;

import static com.codeborne.selenide.Configuration.baseUrl;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.config;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import api.ActionsResponce;

public class BaseTest {

  @Getter
  private static String userIDCookieValueOld;
  @Getter
  private  static String tokenCookieValueOld;
  @Getter
  private  static String expiresCookieValueOld;

  static steps.api.StepsApi stepsApi = new steps.api.StepsApi();

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = cfg.browserSize();
    ActionsResponce responce = stepsApi.authorization(cfg.oldPasswordValue(),
        cfg.oldUserNameValue());
    userIDCookieValueOld = responce.getBodyFieldString("userId");
    tokenCookieValueOld = responce.getBodyFieldString("token");
    expiresCookieValueOld = responce.getBodyFieldString("expires");
  }

  @Step("Чистим кэш")
  @AfterEach
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }
}
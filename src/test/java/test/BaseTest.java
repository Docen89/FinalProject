package test;

import static com.codeborne.selenide.Configuration.baseUrl;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.config;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import steps.api.LowerStepsApi;
import api.ActionsResponce;


public class BaseTest {

  public static String userIDCookieValueOld;
  public static String tokenCookieValueOld;
  public static String expiresCookieValueOld;

  static LowerStepsApi lowerStepsApi = new LowerStepsApi();

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = "2560x1440";
    ActionsResponce responce = lowerStepsApi.authorization(cfg.oldPasswordValue(),
        cfg.oldUserNameValue());
    userIDCookieValueOld = responce.getBodyFieldString("userId");
    tokenCookieValueOld = responce.getBodyFieldString("token");
    expiresCookieValueOld = responce.getBodyFieldString("expires");
  }

  @AfterEach
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }

}
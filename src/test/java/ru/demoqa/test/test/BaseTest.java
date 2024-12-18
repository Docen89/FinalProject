package ru.demoqa.test.test;

import static com.codeborne.selenide.Configuration.baseUrl;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Cookies;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import java.util.Map;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import  helpers.Cookies;

public class BaseTest {


  static Cookies cookies = new Cookies();
  public static Map<String ,String> cookie;
  public static configs.config cfg = ConfigFactory.create(configs.config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = cfg.browserSize();
    cookie = cookies.getCookieOldUser();
  }

  @Step("Чистим кэш")
  @AfterEach
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }
}
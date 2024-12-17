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

  @Step("Чистим кэш")
  @AfterEach
  public void clear() {
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();
  }
}
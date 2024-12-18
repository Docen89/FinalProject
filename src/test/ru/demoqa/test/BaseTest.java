package ru.demoqa.test;

import static com.codeborne.selenide.Configuration.baseUrl;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.demoqa.configs.config;

public class BaseTest {

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = cfg.browserSize();
  }

}
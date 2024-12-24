package ru.demoqa.test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.demoqa.configs.config;
import ru.demoqa.steps.ui.HelpersStepsUI;

public class BaseTest {

  static HelpersStepsUI helpersStepsUI = new HelpersStepsUI();
  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl = cfg.baseUri();
    Configuration.browserSize = cfg.browserSize();
    open("favicon.ico");
  }

}
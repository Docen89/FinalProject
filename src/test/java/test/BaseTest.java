package test;

import static com.codeborne.selenide.Configuration.baseUrl;

import com.codeborne.selenide.Configuration;
import config.config;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {


  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
    Configuration.pageLoadStrategy = cfg.loadStrategy();
    baseUrl=cfg.baseUri();
    Configuration.browserSize = "2560x1440";
    RestAssured.baseURI = "https://demoqa.com";
  }

}
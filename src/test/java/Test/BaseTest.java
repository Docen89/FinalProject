package Test;

import static com.codeborne.selenide.Configuration.baseUrl;

import Config.config;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

  public static config cfg = ConfigFactory.create(config.class);

  @BeforeAll
  public static void setup() {
    SelenideLogger.addListener("allureSelenide",
        new AllureSelenide().screenshots(true).savePageSource(true));
  }
}
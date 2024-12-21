package ru.demoqa.api;

import static ru.demoqa.test.BaseTest.cfg;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

public class Specifications {

  private static final Logger logger = LogManager.getLogger(Specifications.class);
  private static final LogDetail logDetail = LogDetail.ALL;
  private static final PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
  private static final AllureRestAssured allureFilter = new AllureRestAssured().setRequestTemplate(
          "custom-http-request.ftl")  // HTML-шаблон запроса для REST-тестов
      .setResponseTemplate("custom-http-response.ftl"); // HTML-шаблон ответа для REST-тестов


  public static RequestSpecification restRequestSpecAuth(String userName, String password) {
    PreemptiveBasicAuthScheme authPreemptive = new PreemptiveBasicAuthScheme();
    authPreemptive.setUserName(userName);
    authPreemptive.setPassword(password);
    return new RequestSpecBuilder().setContentType(ContentType.JSON).setAuth(authPreemptive)
        .setBaseUri(cfg.baseUri()).addFilter(allureFilter).log(LogDetail.ALL).build();
  }

  public static RequestSpecification restRequestSpec() {
    return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(cfg.baseUri())
        .addFilter(allureFilter)
        .addFilter(new RequestLoggingFilter(logDetail, false, logStream, true))
        .addFilter(new ResponseLoggingFilter(logDetail, false, logStream)).log(LogDetail.ALL)
        .build();
  }

  public static ResponseSpecification responseSpec() {
    return new ResponseSpecBuilder().log(LogDetail.ALL).build();
  }

}




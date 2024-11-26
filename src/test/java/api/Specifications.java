package api;

import static test.BaseTest.cfg;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
  private  static final AllureRestAssured allureFilter = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")  // HTML-шаблон запроса для REST-тестов
      .setResponseTemplate("custom-http-response.ftl"); // HTML-шаблон ответа для REST-тестов

  public static RequestSpecification authRequestSpec(String userName,String password){
    return  restRequestSpec()
        .auth()
        .preemptive()
        .basic(userName, password);
  }

  public static RequestSpecification restRequestSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setBaseUri(cfg.baseUri())
        .addFilter(allureFilter)
        .log(LogDetail.ALL)
        .build();
  }

  public static ResponseSpecification responseSpec() {
    return new ResponseSpecBuilder()
        .log(LogDetail.ALL)
        .build();
  }
}




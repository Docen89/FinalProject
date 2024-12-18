package ru.demoqa.api;

import io.restassured.response.Response;

public interface Condition {

  void check(Response response);

}

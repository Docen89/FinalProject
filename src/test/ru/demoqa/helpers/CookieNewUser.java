package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;


public class CookieNewUser {
  private static CookieNewUser instance;
  private String userIdValueNewUser;
  StepsApi stepsApi = new StepsApi();



  private CookieNewUser() {
    ActionsResponce responceOldUser = stepsApi.authorization(cfg.newPasswordValue(),cfg.newUserNameValue());
    userIdValueNewUser = responceOldUser.getBodyFieldString("userId");
  }

  public static synchronized CookieNewUser getInstance() {
    if (instance == null) {
      instance = new CookieNewUser();
    }
    return instance;
  }

  public String getUserIdValueNewUser(){
    return userIdValueNewUser;
  }

}

package ru.demoqa.helpers;


import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;


public class CookieOldUser {
  private static CookieOldUser instance;
  private String userIdValueOldUser;
  private String tokenValueOldUser;
  private String expiresValueOldUser;
  StepsApi stepsApi = new StepsApi();

  public CookieOldUser() {
    ActionsResponce responceOldUser = stepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue());
    tokenValueOldUser = responceOldUser.getBodyFieldString("token");
    expiresValueOldUser = responceOldUser.getBodyFieldString("expires");
    userIdValueOldUser = responceOldUser.getBodyFieldString("userId");
  }

  public static synchronized CookieOldUser getInstance() {
    if (instance == null) {
      instance = new CookieOldUser();
    }
    return instance;
  }

  public String getUserIdValueOldUser(){
    return userIdValueOldUser;
  }

  public String getTokenValueOldUser(){
    return tokenValueOldUser;
  }

  public String getExpiresValueOldUser(){
    return expiresValueOldUser;
  }

}

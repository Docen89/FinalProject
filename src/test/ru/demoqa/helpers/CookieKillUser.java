package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;


public class CookieKillUser {

  private static CookieKillUser instance;
  private String userIdValueKillUser;
  private String tokenValueKillUser;
  private String expiresValueKillUser;
  StepsApi stepsApi = new StepsApi();


  private CookieKillUser() {
    ActionsResponce responceKillUser = stepsApi.userLoginInfo(cfg.killPasswordValue(),
        cfg.killUserNameValue());
    userIdValueKillUser = responceKillUser.getBodyFieldString("userId");
    tokenValueKillUser = responceKillUser.getBodyFieldString("token");
    expiresValueKillUser = responceKillUser.getBodyFieldString("expires");
  }

  public static synchronized CookieKillUser getInstance() {
    if (instance == null) {
      instance = new CookieKillUser();
    }
    return instance;
  }

  public String getUserIdValueKillUser() {
    return userIdValueKillUser;
  }

  public String getTokenValueKillUser() {
    return tokenValueKillUser;
  }

  public String getExpiresValueKillUser() {
    return expiresValueKillUser;
  }

}

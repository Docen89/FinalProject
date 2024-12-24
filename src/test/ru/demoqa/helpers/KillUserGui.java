package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;


public class KillUserGui {

  private static KillUserGui instance;
  private String userIdValueKillUser;
  private String tokenValueKillUser;
  private String expiresValueKillUser;
  StepsApi stepsApi = new StepsApi();


  private KillUserGui() {
    ActionsResponce responceKillUser = stepsApi.userLoginInfo(
        cfg.killUserNameValue(),
        cfg.killPasswordValue());
    userIdValueKillUser = responceKillUser.getBodyFieldString("userId");
    tokenValueKillUser = responceKillUser.getBodyFieldString("token");
    expiresValueKillUser = responceKillUser.getBodyFieldString("expires");
  }

  public static synchronized KillUserGui getInstance() {
    if (instance == null) {
      instance = new KillUserGui();
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

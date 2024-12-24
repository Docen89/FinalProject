package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;

public class KillUserApi {
  private static KillUserApi instance;
  private String userIdValueApiKillUser;
  StepsApi stepsApi = new StepsApi();


  private KillUserApi() {
    ActionsResponce responceKillUser = stepsApi.userLoginInfo(cfg.apiKillUserNameValue(),
        cfg.apiKillPasswordValue());
    userIdValueApiKillUser = responceKillUser.getBodyFieldString("userId");
  }

    public static synchronized KillUserApi getInstance () {
      if (instance == null) {
        instance = new KillUserApi();
      }
      return instance;
    }

    public String getUserIdValueApiKillUser() {
      return userIdValueApiKillUser;
    }

}

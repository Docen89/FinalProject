package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;

import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;

public class InfoApiUser {
  private static InfoApiUser instance;
  private String userIdValueApiUser;
  StepsApi stepsApi = new StepsApi();

  private InfoApiUser() {
    ActionsResponce responceApiUser = stepsApi.userLoginInfo(cfg.oldUserNameValue(), cfg.oldPasswordValue());
    userIdValueApiUser = responceApiUser.getBodyFieldString("userId");
  }

    public static synchronized InfoApiUser getInstance() {
      if (instance == null) {
        instance = new InfoApiUser();
      }
      return instance;
  }

  public String getUserIdValueApiUser(){
    return userIdValueApiUser;
  }

}

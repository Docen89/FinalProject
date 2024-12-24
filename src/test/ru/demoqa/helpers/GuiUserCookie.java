package ru.demoqa.helpers;

import static ru.demoqa.test.BaseTest.cfg;
import ru.demoqa.api.check.ActionsResponce;
import ru.demoqa.steps.api.StepsApi;


public class GuiUserCookie {

  private static GuiUserCookie instance;
  private String guiUserIdValueNewUser;
  private String guiTokenValueNewUser;
  private String guiExpiresValueNewUser;
  StepsApi stepsApi = new StepsApi();


  private GuiUserCookie() {
    ActionsResponce responceOldUser = stepsApi.userLoginInfo(cfg.guiNewUserNameValue(), cfg.guiNewPasswordValue());
    guiTokenValueNewUser = responceOldUser.getBodyFieldString("token");
    guiExpiresValueNewUser = responceOldUser.getBodyFieldString("expires");
    guiUserIdValueNewUser = responceOldUser.getBodyFieldString("userId");
  }

  public static synchronized GuiUserCookie getInstance() {
    if (instance == null) {
      instance = new GuiUserCookie();
    }
    return instance;
  }

  public String getGuiUserIdValueNewUser() {
    return guiUserIdValueNewUser;
  }

  public String getGuiTokenValueNewUser() {
    return guiTokenValueNewUser;
  }

  public String getGuiExpiresValueNewUser() {
    return guiExpiresValueNewUser;
  }

}

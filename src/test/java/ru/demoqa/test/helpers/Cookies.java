package helpers;

import static ru.demoqa.test.test.BaseTest.cfg;
import java.util.HashMap;
import java.util.Map;
import steps.api.StepsApi;

public class Cookies {

 StepsApi stepsApi = new StepsApi();

  public Map<String, String> getCookieOldUser(){
    api.ActionsResponce responceOldUser = stepsApi.authorization(cfg.oldPasswordValue(),cfg.oldUserNameValue());
    Map<String, String> tokenAndExpiresAndUserIdValue = new HashMap<>();
    tokenAndExpiresAndUserIdValue.put("token", responceOldUser.getBodyFieldString("token"));
    tokenAndExpiresAndUserIdValue.put("expires", responceOldUser.getBodyFieldString("expires"));
    tokenAndExpiresAndUserIdValue.put("userId",responceOldUser.getBodyFieldString("userId"));
    return tokenAndExpiresAndUserIdValue;
  }

}

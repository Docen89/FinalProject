package helpers;

import java.util.Map;
import test.BaseTest;

public class CookieOldUser {
  private static CookieOldUser instance;
  private String userIdValueOldUser;
  private String tokenValueOldUser;
  private String expiresValueOldUser;



  private CookieOldUser() {}
  Map<String, String> tokenAndExpiresAndUserIdValue = BaseTest.getCookieOldUser();
  public static synchronized CookieOldUser getInstance() {
    if (instance == null) {
      instance = new CookieOldUser();
    }
    return instance;
  }

  public String getUserIdValueOldUser() {
    if (userIdValueOldUser == null) {
      userIdValueOldUser = tokenAndExpiresAndUserIdValue.get("userId");
    }
    return userIdValueOldUser;
  }

  public String getTokenValueOldUser() {
    if (userIdValueOldUser == null) {
      tokenValueOldUser = tokenAndExpiresAndUserIdValue.get("token");
    }
    return tokenValueOldUser;
  }

  public String getExpiresValueOldUser() {
    if (userIdValueOldUser == null) {
      expiresValueOldUser = tokenAndExpiresAndUserIdValue.get("expires");
    }
    return expiresValueOldUser;
  }

}

package helpers;

import java.util.Map;
import ru.demoqa.test.test.BaseTest;

public class CookieOldUser {
  private static CookieOldUser instance;
  private String userIdValueOldUser;
  private String tokenValueOldUser;
  private String expiresValueOldUser;



  private CookieOldUser() {}

  public static synchronized CookieOldUser getInstance() {
    if (instance == null) {
      instance = new CookieOldUser();
    }
    return instance;
  }

  public String getUserIdValueOldUser() {
    if (userIdValueOldUser == null) {
      userIdValueOldUser = BaseTest.cookie.get("userId");
    }
    return userIdValueOldUser;
  }

  public String getTokenValueOldUser() {
    if (userIdValueOldUser == null) {
      tokenValueOldUser = BaseTest.cookie.get("token");
    }
    return tokenValueOldUser;
  }

  public String getExpiresValueOldUser() {
    if (userIdValueOldUser == null) {
      expiresValueOldUser = BaseTest.cookie.get("expires");
    }
    return expiresValueOldUser;
  }

}

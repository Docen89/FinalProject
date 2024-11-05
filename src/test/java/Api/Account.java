package Api;


import Test.BaseTest;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import Model.RestAssuredModel;


public class Account extends BaseTest {

  RestAssuredModel restAssuredModel = new RestAssuredModel();
  String userIdDelete;


  public Map<String, String> createNewUser() {
    Response response = restAssuredModel.newUser(cfg.baseUri(), cfg.newUserNameValue(),
        cfg.newUserPassword(),
        cfg.userPath());
    Map<String, String> userIdAndUserNameAndBooksAndStatus = new HashMap<>();
    userIdAndUserNameAndBooksAndStatus.put("userIdValue", response.path("userID"));
    userIdDelete = response.path("userID");
    userIdAndUserNameAndBooksAndStatus.put("userNameValue", response.path("username"));
    return userIdAndUserNameAndBooksAndStatus;
  }

  public Map<String, String> authorized() {
    Response response = restAssuredModel.authorization(cfg.baseUri(), cfg.userNameValue(),
        cfg.passwordValue(),
        cfg.loginPath());
    Map<String, String> tokenAndExpiresAndUserIdAndStatusValue = new HashMap<>();
    tokenAndExpiresAndUserIdAndStatusValue.put("token", response.path("token"));
    tokenAndExpiresAndUserIdAndStatusValue.put("expires", response.path("expires"));
    tokenAndExpiresAndUserIdAndStatusValue.put("userId", response.path("userId"));
    tokenAndExpiresAndUserIdAndStatusValue.put("status", String.valueOf(response.getStatusCode()));
    return tokenAndExpiresAndUserIdAndStatusValue;
  }

  public Map<String, String> deleteUser() {
    Response response = restAssuredModel.deleteUser(cfg.baseUri(), cfg.newUserNameValue(),
        cfg.newUserPassword(),
        cfg.userPath() + userIdDelete);
    Map<String, String> responseDeleteUser = new HashMap<>();
    responseDeleteUser.put("StatusValue", String.valueOf(response.getStatusCode()));
    return responseDeleteUser;
  }


}

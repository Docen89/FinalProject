package Api;


import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import model.RestAssuredModel;


public class Account {
  String userIdDelete;
  RestAssuredModel restAssuredModel = new RestAssuredModel();

  public Map<String, String> createNewUser() {
    Response response = restAssuredModel.newUser("Docen90", "Docen1313!",
        "https://demoqa.com/Account/v1/User");
    Map<String, String> userIdAndUserNameAndBooksAndStatus = new HashMap<>();
    userIdAndUserNameAndBooksAndStatus.put("userIdValue", response.path("userID"));
    userIdDelete =response.path("userID");
    userIdAndUserNameAndBooksAndStatus.put("userNameValue", response.path("username"));
    return userIdAndUserNameAndBooksAndStatus;
  }

  public Map<String, String> authorized() {
    Response response = restAssuredModel.authorization("Docen89","Docen1313!",
        "https://demoqa.com/Account/v1/Login");
    Map<String, String> tokenAndExpiresAndUserIdAndStatusValue = new HashMap<>();
    tokenAndExpiresAndUserIdAndStatusValue.put("token", response.path("token"));
    tokenAndExpiresAndUserIdAndStatusValue.put("expires", response.path("expires"));
    tokenAndExpiresAndUserIdAndStatusValue.put("userId", response.path("userId"));
    tokenAndExpiresAndUserIdAndStatusValue.put("status", String.valueOf(response.getStatusCode()));
    return tokenAndExpiresAndUserIdAndStatusValue;
  }

  public Map<String, String> deleteUser() {
    Response response = restAssuredModel.deleteUser("Docen90", "Docen1313!",
        "https://demoqa.com/Account/v1/User/"+userIdDelete);
    Map<String, String> responseDeleteUser = new HashMap<>();
    responseDeleteUser.put("StatusValue", String.valueOf(response.getStatusCode()));
    return responseDeleteUser;
  }


}

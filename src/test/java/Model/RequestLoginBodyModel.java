package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestLoginBodyModel {

  @JsonProperty("password")
  private String password;

  @JsonProperty("userName")
  private String userName;

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }
}
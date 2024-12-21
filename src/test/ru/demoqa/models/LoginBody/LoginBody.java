package ru.demoqa.models.LoginBody;

import ru.demoqa.models.Login.LoginModel;

public class LoginBody {

  public LoginModel LoginBody(String password, String userName) {
    LoginModel loginModel = new LoginModel();
    loginModel.setPassword(password);
    loginModel.setUserName(userName);
    return loginModel;
  }
}

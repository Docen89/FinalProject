package ru.demoqa.models.authorizedBody;


import ru.demoqa.models.authorized.RequestAuthorizedBodyModel;

public class RequestAuthorizedBody {

  public RequestAuthorizedBodyModel RequestAuthorizedBody(String passwordValue, String userName) {
    RequestAuthorizedBodyModel requestAuthorizedBodyModel = new RequestAuthorizedBodyModel();
    requestAuthorizedBodyModel.setUserName(userName);
    requestAuthorizedBodyModel.setPassword(passwordValue);
    return requestAuthorizedBodyModel;
  }

}

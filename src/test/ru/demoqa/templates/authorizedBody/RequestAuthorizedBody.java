package ru.demoqa.templates.authorizedBody;


import ru.demoqa.models.authorized.RequestAuthorizedBodyModel;

public class RequestAuthorizedBody {

  public RequestAuthorizedBodyModel RequestAuthorizedBody(String userName, String passwordValue) {
    RequestAuthorizedBodyModel requestAuthorizedBodyModel = new RequestAuthorizedBodyModel();
    requestAuthorizedBodyModel.setUserName(userName);
    requestAuthorizedBodyModel.setPassword(passwordValue);
    return requestAuthorizedBodyModel;
  }

}

package templates.request.authorizedBody;

import models.request.authorized.RequestAuthorizedBodyModel;

public class RequestAuthorizedBody {

  public RequestAuthorizedBodyModel RequestAuthorizedBody(String passwordValue, String userName) {
    RequestAuthorizedBodyModel requestAuthorizedBodyModel = new RequestAuthorizedBodyModel();
    requestAuthorizedBodyModel.setUserName(userName);
    requestAuthorizedBodyModel.setPassword(passwordValue);
    return requestAuthorizedBodyModel;
  }

}

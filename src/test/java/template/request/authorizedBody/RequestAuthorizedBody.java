package template.request.authorizedBody;

import model.request.authorized.RequestAuthorizedBodyModel;

public class RequestAuthorizedBody {

  public Object completionRequestAuthorizedBody(String passwordValue, String userName) {
    RequestAuthorizedBodyModel requestAuthorizedBodyModel = new RequestAuthorizedBodyModel();
    requestAuthorizedBodyModel.setUserName(userName);
    requestAuthorizedBodyModel.setPassword(passwordValue);
    return requestAuthorizedBodyModel;

  }

}

package template.completion.request.authorizedBody;

import static test.BaseTest.cfg;

import model.request.authorized.RequestAuthorizedBodyModel;

public class RequestAuthorizedBody {

  public Object completionRequestAuthorizedBody(){
    RequestAuthorizedBodyModel requestAuthorizedBodyModel = new RequestAuthorizedBodyModel();
    requestAuthorizedBodyModel.setPassword(cfg.passwordValue());
    requestAuthorizedBodyModel.setUserName(cfg.userNameValue());
    return  requestAuthorizedBodyModel;
  }

}
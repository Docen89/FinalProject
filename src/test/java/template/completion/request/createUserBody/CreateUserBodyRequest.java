package template.completion.request.createUserBody;

import static test.BaseTest.cfg;

import model.request.createNewUser.RequestCreateUserBodyModel;

public class CreateUserBodyRequest {

  public Object completionRequestCreateUserBody(){
    RequestCreateUserBodyModel requestCreateUserBodyModel = new RequestCreateUserBodyModel();
    requestCreateUserBodyModel.setUserName(cfg.newUserNameValue());
    requestCreateUserBodyModel.setPassword(cfg.newUserPassword());
    return requestCreateUserBodyModel;
  }

}
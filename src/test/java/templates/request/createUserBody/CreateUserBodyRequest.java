package templates.request.createUserBody;

import static test.BaseTest.cfg;

import models.request.createNewUser.RequestCreateUserBodyModel;

public class CreateUserBodyRequest {

  public RequestCreateUserBodyModel RequestCreateUserBody() {
    RequestCreateUserBodyModel requestCreateUserBodyModel = new RequestCreateUserBodyModel();
    requestCreateUserBodyModel.setUserName(cfg.newUserNameValue());
    requestCreateUserBodyModel.setPassword(cfg.newPasswordValue());
    return requestCreateUserBodyModel;
  }

}

package ru.demoqa.templates.createUserBody;
import ru.demoqa.models.createNewUser.RequestCreateUserBodyModel;

public class CreateUserBodyRequest {

  public RequestCreateUserBodyModel RequestCreateUserBody(String password, String userName) {
   RequestCreateUserBodyModel requestCreateUserBodyModel = new RequestCreateUserBodyModel();
    requestCreateUserBodyModel.setUserName(userName);
    requestCreateUserBodyModel.setPassword(password);
    return requestCreateUserBodyModel;
  }

}

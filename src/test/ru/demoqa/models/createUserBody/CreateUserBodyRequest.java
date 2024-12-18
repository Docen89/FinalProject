package ru.demoqa.models.createUserBody;
import ru.demoqa.models.createNewUser.RequestCreateUserBodyModel;

public class CreateUserBodyRequest {

  public RequestCreateUserBodyModel RequestCreateUserBody(String userName,String password) {
   RequestCreateUserBodyModel requestCreateUserBodyModel = new RequestCreateUserBodyModel();
    requestCreateUserBodyModel.setUserName(userName);
    requestCreateUserBodyModel.setPassword(password);
    return requestCreateUserBodyModel;
  }

}

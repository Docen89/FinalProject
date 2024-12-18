package ru.demoqa.models.createNewUser;

import lombok.Data;

@Data
public class RequestCreateUserBodyModel {

  private String password;
  private String userName;


}
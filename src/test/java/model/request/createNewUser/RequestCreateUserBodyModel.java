package model.request.createNewUser;

import lombok.Data;

@Data
public class RequestCreateUserBodyModel {
	private String password;
	private String userName;
}
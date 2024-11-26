package model.request.authorized;

import lombok.Data;

@Data
public class RequestAuthorizedBodyModel {
	private String userName;
	private String password;

}
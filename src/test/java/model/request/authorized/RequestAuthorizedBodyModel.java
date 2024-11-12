package model.request.authorized;

import lombok.Data;

@Data
public class RequestAuthorizedBodyModel {
	private String password;
	private String userName;
}
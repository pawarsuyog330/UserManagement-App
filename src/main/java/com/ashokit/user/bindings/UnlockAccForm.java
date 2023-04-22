package com.ashokit.user.bindings;

import lombok.Data;

@Data
public class UnlockAccForm {

	private String userEmail;

	private String tempPwd;

	private String newPwd;

	private String confirmNewPwd;

}
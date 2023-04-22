package com.ashokit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.user.service.UserMgmtService;

@RestController
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtService userService;
	
	@GetMapping("/forgotPwd/{userEmail}")
	public String forgotPwd(@PathVariable String userEmail)
	{
		return userService.forgotPwd(userEmail);
	}
	
	
}

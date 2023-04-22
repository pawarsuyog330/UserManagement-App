package com.ashokit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.user.bindings.LoginForm;
import com.ashokit.user.service.UserMgmtService;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserMgmtService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm)
	{
		return userService.loginCheck(loginForm);
	}

}

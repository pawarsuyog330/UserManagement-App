package com.ashokit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.user.bindings.UnlockAccForm;
import com.ashokit.user.service.UserMgmtService;

@RestController
public class UnlockAccRestController {
	
	@Autowired
	private UserMgmtService userService;
	
	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccForm unlockAccForm)
	{
		return userService.unlockAcc(unlockAccForm);
	}

}

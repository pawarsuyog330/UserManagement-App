package com.ashokit.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.user.bindings.UserRegForm;
import com.ashokit.user.service.UserMgmtService;

@RestController
public class RegistrationRestController {
	
	@Autowired
	private UserMgmtService userService;
	
	@GetMapping("/userEmail/{userEmail}")
	public String emailCheck(@PathVariable("userEmail") String userEmail )
	{
		return userService.emailCheck(userEmail);
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountries()
	{
		return userService.loadCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getStates(@PathVariable("countryId") Integer countryId)
	{
		return userService.loadStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCities(@PathVariable("stateId") Integer stateId)
	{
		return userService.loadCities(stateId);
	}
	
	@PostMapping("/register")
	public String userRegistration(@RequestBody UserRegForm userRegForm)
	{
		return userService.saveUser(userRegForm);
	}

}

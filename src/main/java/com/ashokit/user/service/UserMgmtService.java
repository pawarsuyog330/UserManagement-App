package com.ashokit.user.service;

import java.util.Map;

import com.ashokit.user.bindings.LoginForm;
import com.ashokit.user.bindings.UnlockAccForm;
import com.ashokit.user.bindings.UserRegForm;

public interface UserMgmtService {
	
	//login screen related methods
	public String loginCheck(LoginForm loginForm);
	
	//Registration screen related methods
	public String emailCheck(String userEmail);
	
	public Map<Integer, String> loadCountries();
	
	public Map<Integer, String> loadStates(Integer countryId);
	
	public Map<Integer, String> loadCities(Integer stateId);
	
	public String saveUser(UserRegForm userRegForm);
	
	//Unlock account screen related methods
	public String unlockAcc(UnlockAccForm unlockAccForm);
	
	//Forgot password screen related methods
	public String forgotPwd(String userEmail);

}
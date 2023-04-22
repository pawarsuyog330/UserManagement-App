package com.ashokit.user.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.user.bindings.LoginForm;
import com.ashokit.user.bindings.UnlockAccForm;
import com.ashokit.user.bindings.UserRegForm;
import com.ashokit.user.entity.CityMaster;
import com.ashokit.user.entity.CountryMaster;
import com.ashokit.user.entity.StateMaster;
import com.ashokit.user.entity.UserDetails;
import com.ashokit.user.repository.CityEntityRepository;
import com.ashokit.user.repository.CountryEntityRepository;
import com.ashokit.user.repository.StateEntityRepository;
import com.ashokit.user.repository.UserEntityRepository;
import com.ashokit.user.service.UserMgmtService;
import com.ashokit.user.utils.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	public UserEntityRepository userRepo;

	@Autowired
	public CountryEntityRepository countryRepo;

	@Autowired
	public StateEntityRepository stateRepo;
	
	@Autowired
	public CityEntityRepository cityRepo;
	
	@Autowired
	public EmailUtils emailUtils;

	@Override
	public String loginCheck(LoginForm loginForm) {
		
		UserDetails userDetails = userRepo.findByUserEmailAndPazzword(loginForm.getUserEmail(), loginForm.getPazzword());

		if (userDetails == null) {
			return "Invalid Credentials";
		}

		if (userDetails != null && userDetails.getAccStatus().equals("LOCKED")) {
			return "YOUR ACCOUNT IS LOCKED";
		}
		return "SUCCESS";
	}

	@Override
	public String emailCheck(String userEmail) {
		
		UserDetails entity = userRepo.findByUserEmail(userEmail);
		
		if (entity == null) {
			return "UNIQUE EMAIL ID";
		}
		return "DUPLICATE EMAIL ID";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		
		List<CountryMaster> list = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		
		for (CountryMaster entity : list) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {
		
		List<StateMaster> stateList = stateRepo.findStateByCountryId(countryId);
		
		Map<Integer, String> stateMap=new HashMap<>();
		
		for(StateMaster stateEntity : stateList)
		{
			stateMap.put(stateEntity.getStateId(), stateEntity.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		
		List<CityMaster> cityList = cityRepo.findCityByStateId(stateId);
		
		Map<Integer, String> cityMap= new HashMap<>();
		
		for(CityMaster cityEntity: cityList)
		{
			cityMap.put(cityEntity.getCityId(), cityEntity.getCityName());
		}
		return cityMap;
	}

	@Override
	public String saveUser(UserRegForm userRegForm) {
		UserDetails userDetails= new UserDetails();
		
		BeanUtils.copyProperties(userRegForm, userDetails);
		
		userDetails.setAccStatus("LOCKED");
		
		userDetails.setPazzword(generateRandomPwd());
		
		UserDetails entity= userRepo.save(userDetails);
		
		String userEmail = userRegForm.getUserEmail();
		
		String subject= "User Registration";
		
		String fileName="Registration.txt";
		
		String body= readMailBodyContent(fileName, userDetails);
		
		boolean isSent = emailUtils.sendEmail(userEmail, subject, body);
		
		if(entity.getUserId()!=null && isSent)
			{
			return "SUCCESS";
			};
		
		return "FAIL";
	}

	@Override
	public String unlockAcc(UnlockAccForm unlockAccForm) {
		
		if(!(unlockAccForm.getNewPwd().equals(unlockAccForm.getConfirmNewPwd())))
		{
			return "Password and Confirm Password should be same";
		}
		
		UserDetails unlockAccount = userRepo.findByUserEmailAndPazzword(unlockAccForm.getUserEmail(), unlockAccForm.getTempPwd());
		
		if(unlockAccount==null)
		{
			return "Incorrect Temporary Password";
		}
		
		unlockAccount.setPazzword(unlockAccForm.getNewPwd());
		unlockAccount.setAccStatus("UNLOCKED");
		
		userRepo.save(unlockAccount);
		return "Account unlocked, please proceed with login";
	}

	@Override
	public String forgotPwd(String userEmail) {
		
		UserDetails entity = userRepo.findByUserEmail(userEmail);
		
		if(entity == null)
		{
			return "Invalid Email Id";
		}
		
		// If Record available send password to user email
		
		String fileName="Password.txt";
		String body = readMailBodyContent(fileName, entity);
		String subject ="Recover Password";
		
		boolean isSent = emailUtils.sendEmail(userEmail, subject, body);
		
		if(isSent)
		{
			return "Password sent to registered Email Id";
		}
		
		
		return "ERROR";
	}
	
	public String generateRandomPwd()
	{
		int leftLimit=48; // numeral '0'
		int rightlimit=122; //letter 'z'
		int targetStringLength=6;
		Random random = new Random();
		
		String generatedString = random.ints(leftLimit, rightlimit+1)
								.filter(i->(i<=57 || i>=65) && (i<=90 || i>=97))
								.limit(targetStringLength)
								.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
								.toString();
		
		return generatedString;
	}
	
	private String readMailBodyContent(String fileName, UserDetails entity)
	{
		String mailBody=null;
		try {
			
			StringBuffer sb=new StringBuffer();
			
			FileReader fr= new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			
			String line=br.readLine(); //Reading first line data
			
			while(line!=null)
			{
				sb.append(line); //appending line data to buffer object
				line=br.readLine(); //reading next line data
			}
			
			mailBody=sb.toString();
			mailBody=mailBody.replace("{FIRSTNAME}", entity.getFirstName());
			mailBody=mailBody.replace("{LASTNAME}", entity.getLastName());
			mailBody=mailBody.replace("{TEMP-PWD}", entity.getPazzword());
			mailBody=mailBody.replace("{USEREMAIL}", entity.getUserEmail());
			mailBody=mailBody.replace("{PWD}", entity.getPazzword());
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mailBody;
	}

}
package com.ashokit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.user.entity.UserDetails;

@Repository
public interface UserEntityRepository extends JpaRepository<UserDetails, Integer> {
	
	//select * from user_accounts where user_email=? and user_pwd=?
	public UserDetails findByUserEmailAndPazzword(String userEmail, String pazzword);
	
	//select * from user_accounts where user_email=?
	public UserDetails findByUserEmail(String userEmail);

}

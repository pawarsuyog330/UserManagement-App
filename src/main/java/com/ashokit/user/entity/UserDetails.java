package com.ashokit.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="USERDETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull
	private String userEmail;
	
	private String userPassword;
	
	private Long userMobile;
	
	private Date dateOfBirth;
	
	private Boolean gender;
	
	private Integer cityId;
	
	private Integer stateId;
	
	private Integer countryId;
	
	private Boolean accountStatus;
	
	private Date createdDate;
	
	private Date updatedDate;
	
}
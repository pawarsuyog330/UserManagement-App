package com.ashokit.user.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="USER_ACCOUNTS")
public class UserDetails {

	@Id
	@GeneratedValue
	private Integer userId;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	@Email
	private String userEmail;
	
	@Column(name="USER_PWD")
	private String pazzword;
	
	@Column(name="USER_MOBILE")
	private Long phno;
	
	@Column(name="DOB")
	private LocalDate dob;
	
	private String gender;
	
	private Integer cityId;
	
	private Integer stateId;
	
	private Integer countryId;
	
	private String accStatus;
	
	@Column(name="CREATED_DATE", updatable=false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
}
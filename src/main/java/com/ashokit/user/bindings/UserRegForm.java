package com.ashokit.user.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegForm {

	private String firstName;

	private String lastName;

	private String userEmail;

	private Long phno;
	
	private LocalDate dob;

	private String gender;

	private Integer cityId;

	private Integer stateId;

	private Integer countryId;
}
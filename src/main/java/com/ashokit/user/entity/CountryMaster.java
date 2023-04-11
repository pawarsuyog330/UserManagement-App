package com.ashokit.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_DETAILS")
public class CountryMaster {

	@Id
	private Integer countryId;
	
	private String countryName;
}

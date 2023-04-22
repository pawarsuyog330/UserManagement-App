package com.ashokit.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.user.entity.StateMaster;

@Repository
public interface StateEntityRepository extends JpaRepository<StateMaster, Integer> {

	// select * from states_master where country_id=?
	public List<StateMaster> findStateByCountryId(Integer countryId);
}

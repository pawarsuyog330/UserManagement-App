package com.ashokit.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.user.entity.CityMaster;

@Repository
public interface CityEntityRepository extends JpaRepository<CityMaster, Integer> {

	// select * from city_master where state_id=?
		public List<CityMaster> findCityByStateId(Integer stateId);
}

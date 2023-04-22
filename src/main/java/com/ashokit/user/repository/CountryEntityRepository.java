package com.ashokit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.user.entity.CountryMaster;

@Repository
public interface CountryEntityRepository extends JpaRepository<CountryMaster, Integer> {

}

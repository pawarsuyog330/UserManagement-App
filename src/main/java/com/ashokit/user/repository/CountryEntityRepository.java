package com.ashokit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.user.entity.CountryMaster;

public interface CountryEntityRepository extends JpaRepository<CountryMaster, Integer> {

}

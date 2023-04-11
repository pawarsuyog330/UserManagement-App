package com.ashokit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.user.entity.StateMaster;

public interface StateEntityRepository extends JpaRepository<StateMaster, Integer> {

}

package com.ashokit.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.user.entity.UserDetails;

public interface UserEntityRepository extends JpaRepository<UserDetails, Integer> {

}

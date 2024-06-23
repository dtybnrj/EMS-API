package com.aditya.emsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aditya.emsapi.model.EMSUser;

public interface EMSUserRepo extends JpaRepository<EMSUser, Integer> {
	
	Optional<EMSUser> findByEmail(String email);

}

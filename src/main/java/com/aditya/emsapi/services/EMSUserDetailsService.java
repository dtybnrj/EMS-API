package com.aditya.emsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.EMSUser;
import com.aditya.emsapi.repository.EMSUserRepo;

@Service
public class EMSUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EMSUserRepo emsUserRepo;

	@Override
	public EMSUser loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return emsUserRepo.findByEmail(username).orElseThrow();
	}

}

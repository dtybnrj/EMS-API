package com.aditya.emsapi.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.EMSUser;
import com.aditya.emsapi.repository.EMSUserRepo;
import com.aditya.emsapi.view.EMSUserReqRes;

@Service
public class EMSUserSeviceImpl {

	@Autowired
	private EMSUserRepo emsUserRepo;
	@Autowired
	private JWTUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
    public EMSUserReqRes register(EMSUserReqRes registrationRequest){
    	EMSUserReqRes resp = new EMSUserReqRes();

        try {
            EMSUser emsUser = new EMSUser();
            emsUser.setEmail(registrationRequest.getEmail());
            emsUser.setCity(registrationRequest.getCity());
            emsUser.setRole(registrationRequest.getRole());
            emsUser.setName(registrationRequest.getName());
            emsUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            EMSUser emsUserResult = emsUserRepo.save(emsUser);
            if (emsUserResult.getId()>0) {
                resp.setEmsUsers(emsUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
    
    public EMSUserReqRes login(EMSUserReqRes loginRequest){
    	EMSUserReqRes response = new EMSUserReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = emsUserRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public void logout(String token){

        String jwtToken = token.substring(7);

        jwtUtils.blacklistToken(jwtToken);

    }
    
    public EMSUserReqRes getAllUsers() {
    	EMSUserReqRes reqRes = new EMSUserReqRes();

        try {
            List<EMSUser> result = emsUserRepo.findAll();
            if (!result.isEmpty()) {
                reqRes.setEmsUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }
    
    public EMSUserReqRes getUsersById(Integer id) {
    	EMSUserReqRes reqRes = new EMSUserReqRes();
        try {
            EMSUser usersById = emsUserRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setEmsUsers(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

}

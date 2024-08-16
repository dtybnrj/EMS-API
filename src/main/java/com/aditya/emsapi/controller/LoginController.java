package com.aditya.emsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.emsapi.services.EMSUserSeviceImpl;
import com.aditya.emsapi.view.EMSUserReqRes;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
	EMSUserSeviceImpl emsUserServiceimpl;

    @PostMapping("/login")
    public ResponseEntity<EMSUserReqRes> login(@RequestBody EMSUserReqRes req){
        return ResponseEntity.ok(emsUserServiceimpl.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<EMSUserReqRes>  register(@RequestBody EMSUserReqRes ems) {
        
        
        return ResponseEntity.ok(emsUserServiceimpl.register(ems));
    }
    

}

package com.aditya.emsapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya.emsapi.model.Role;
import com.aditya.emsapi.model.User;
import com.aditya.emsapi.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) return "Email already taken";
        user.setRole(Role.ADMIN);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
            return jwtUtil.generateToken(user.get(), user.get().getRole().toString());
        }
        return "Invalid credentials!";
    }

    public Map<String, Boolean> validateToken(Map<String, String> request) {
        String token = request.get("token");
        boolean isValid = jwtUtil.isTokenExpired(token);
        return Map.of("valid", isValid);
    }
}


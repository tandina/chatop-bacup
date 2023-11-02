package com.chatopreview.api.api.service.impl;

import com.chatopreview.api.api.dto.JwtAuthenticationResponse;
import com.chatopreview.api.api.dto.RefreshTokenRequest;
import com.chatopreview.api.api.dto.SignUpRequest;
import com.chatopreview.api.api.dto.SigninRequest;
import com.chatopreview.api.api.entities.Role;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.repository.UserRepository;
import com.chatopreview.api.api.service.AuthenticationService;
import com.chatopreview.api.api.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup (SignUpRequest signupRequest){
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return userRepository.save(user);
    }
    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("email invalide"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user=userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
        var jwt=jwtService.generateToken(user);

        JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
        return jwtAuthenticationResponse;
        }
        return null;
    }
}

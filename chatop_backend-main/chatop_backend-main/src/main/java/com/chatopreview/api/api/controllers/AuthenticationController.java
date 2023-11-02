package com.chatopreview.api.api.controllers;

import com.chatopreview.api.api.dto.JwtAuthenticationResponse;
import com.chatopreview.api.api.dto.RefreshTokenRequest;
import com.chatopreview.api.api.dto.SignUpRequest;
import com.chatopreview.api.api.dto.SigninRequest;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        try {
            User user = authenticationService.signup(signUpRequest);
            return ResponseEntity.ok(user);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Un compte avec cet email existe déjà.", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/signing")
    public ResponseEntity<JwtAuthenticationResponse> signing(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }


}

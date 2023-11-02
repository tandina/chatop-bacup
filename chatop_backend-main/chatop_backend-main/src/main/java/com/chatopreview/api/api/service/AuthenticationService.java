package com.chatopreview.api.api.service;

import com.chatopreview.api.api.dto.JwtAuthenticationResponse;
import com.chatopreview.api.api.dto.RefreshTokenRequest;
import com.chatopreview.api.api.dto.SignUpRequest;
import com.chatopreview.api.api.dto.SigninRequest;
import com.chatopreview.api.api.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}

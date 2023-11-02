package com.chatopreview.api.api.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;

}

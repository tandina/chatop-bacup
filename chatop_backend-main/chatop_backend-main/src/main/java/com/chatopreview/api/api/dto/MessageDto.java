package com.chatopreview.api.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Integer userId;
    private Integer rentalId;
    private String message;
}
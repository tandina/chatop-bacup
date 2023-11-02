package com.chatopreview.api.api.dto;

import lombok.Data;

@Data
public class RentalDto {
    private int id;
    private String name;
    private Number surface;
    private Number price;
    private String picture;
    private String description;
    private int ownerId;
}

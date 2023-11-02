package com.chatopreview.api.api.exceptions;

public class RentalNotFoundException extends RuntimeException{
    private static final long serialVersionVID =1;

    public RentalNotFoundException(String message) {
        super(message);
    }
}

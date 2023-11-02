package com.chatopreview.api.api.service;

import com.chatopreview.api.api.dto.RentalDto;

import java.util.List;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);
    List<RentalDto> getAllRental();
    RentalDto getRentalById(int id);
    RentalDto updateRental(RentalDto rentalDto, int id);

}

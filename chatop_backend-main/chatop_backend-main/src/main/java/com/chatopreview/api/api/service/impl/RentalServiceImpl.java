package com.chatopreview.api.api.service.impl;

import com.chatopreview.api.api.dto.RentalDto;
import com.chatopreview.api.api.exceptions.RentalNotFoundException;
import com.chatopreview.api.api.models.Rental;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.repository.RentalRepository;
import com.chatopreview.api.api.service.RentalService;
import com.chatopreview.api.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final UserService userService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.userService = userService; 
    }

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        rental.setPicture(rentalDto.getPicture());
        rental.setDescription(rentalDto.getDescription());
        User owner = userService.findById(rentalDto.getOwnerId());
        rental.setOwner(owner);

        Rental newRental = rentalRepository.save(rental);

        RentalDto rentalResponse = new RentalDto();
        rentalResponse.setId(newRental.getId());
        rentalResponse.setName(newRental.getName());
        rentalResponse.setSurface(newRental.getSurface());
        rentalResponse.setPrice(newRental.getPrice());
        rentalResponse.setPicture(newRental.getPicture());
        rentalResponse.setDescription(newRental.getDescription());
        rentalResponse.setOwnerId(newRental.getOwnerId());

        return rentalResponse;
    }

    @Override
    public List<RentalDto> getAllRental() {
        List<Rental> rental= rentalRepository.findAll();
        return rental.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public RentalDto getRentalById(int id) {
        Rental rental= rentalRepository.findById(id).orElseThrow(() -> new RentalNotFoundException("Annonce introuvable"));
        return mapToDto(rental);
    }

    @Override
    public RentalDto updateRental(RentalDto rentalDto, int id) {
        Rental rental= rentalRepository.findById(id).orElseThrow(()-> new RentalNotFoundException("L'annonce ne peux être mis à jour"));

        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        rental.setDescription(rentalDto.getDescription());
        rental.setPicture(rentalDto.getPicture());
        User owner = userService.findById(rentalDto.getOwnerId());
        rental.setOwner(owner);

        Rental updatedRental =rentalRepository.save(rental);
        return mapToDto(updatedRental);
    }

    private RentalDto mapToDto(Rental rental) {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setId(rental.getId());
        rentalDto.setName(rental.getName());
        rentalDto.setSurface(rental.getSurface());
        rentalDto.setPrice(rental.getPrice());
        rentalDto.setDescription(rental.getDescription());
        rentalDto.setPicture(rental.getPicture());
        rentalDto.setOwnerId(rental.getOwnerId());
        return rentalDto;
    }

    private Rental mapToEntity(RentalDto rentalDto) {
        Rental rental = new Rental();
        rental.setId(rentalDto.getId());
        rental.setName(rentalDto.getName());
        rental.setSurface(rentalDto.getSurface());
        rental.setPrice(rentalDto.getPrice());
        rental.setDescription(rentalDto.getDescription());
        rental.setPicture(rentalDto.getPicture());
        User owner = userService.findById(rentalDto.getOwnerId());
        rental.setOwner(owner);
        return rental;
    }
}
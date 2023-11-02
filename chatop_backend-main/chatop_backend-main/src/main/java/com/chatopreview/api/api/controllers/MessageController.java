package com.chatopreview.api.api.controllers;

import com.chatopreview.api.api.dto.MessageDto;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.models.Message;
import com.chatopreview.api.api.models.Rental;
import com.chatopreview.api.api.repository.MessageRepository;
import com.chatopreview.api.api.repository.RentalRepository;
import com.chatopreview.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto messageDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalName).orElse(null);
        Rental rental = rentalRepository.findById(messageDto.getRentalId()).orElse(null);

        if (user != null && rental != null && rental.getOwner() != null) {
            if (user.getId().equals(rental.getOwner().getId())) {
                return ResponseEntity.badRequest().body(null);
            }

            Message message = new Message();
            message.setUser(user);
            message.setRental(rental);
            message.setMessage(messageDto.getMessage());
            System.out.println("Message envoyé avec succès");
            return ResponseEntity.ok(messageRepository.save(message));

        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
package com.chatopreview.api.api.service.impl;


import com.chatopreview.api.api.dto.UserDto;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.exceptions.RentalNotFoundException;
import com.chatopreview.api.api.models.Message;
import com.chatopreview.api.api.models.Rental;
import com.chatopreview.api.api.repository.MessageRepository;
import com.chatopreview.api.api.repository.UserRepository;
import com.chatopreview.api.api.repository.RentalRepository;
import com.chatopreview.api.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public Message createMessage(Long userId, Long rentalId, String content) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RentalNotFoundException("user introuvable"));
        user.getRentals().size();
        Rental rental = rentalRepository.findById(Math.toIntExact(rentalId)).orElseThrow(()-> new RentalNotFoundException("annonce introuvable"));

        Message message = new Message();
        message.setUser(user);
        message.setRental(rental);
        message.setMessage(content);

        return messageRepository.save(message);
    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new RentalNotFoundException("user introuvable"));
        return new UserDto(user.getId(), user.getEmail());
    }
}
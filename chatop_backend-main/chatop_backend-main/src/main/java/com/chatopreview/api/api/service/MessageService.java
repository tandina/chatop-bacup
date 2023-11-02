package com.chatopreview.api.api.service;
import com.chatopreview.api.api.dto.UserDto;
import com.chatopreview.api.api.models.Message;

public interface MessageService {
    Message createMessage(Long userId, Long rentalId, String content);
    UserDto getUser(Long userId);
}
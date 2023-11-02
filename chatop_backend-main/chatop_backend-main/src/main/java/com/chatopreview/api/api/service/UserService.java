package com.chatopreview.api.api.service;
import com.chatopreview.api.api.entities.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User findById(int id);
}

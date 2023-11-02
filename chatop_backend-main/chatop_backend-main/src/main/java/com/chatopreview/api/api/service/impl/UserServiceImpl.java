package com.chatopreview.api.api.service.impl;

import com.chatopreview.api.api.repository.UserRepository;
import com.chatopreview.api.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.chatopreview.api.api.entities.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("utilisateur introuvable"));
            }
        };
    }
    @Override
    public User findById(int id) {
        return userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }
}

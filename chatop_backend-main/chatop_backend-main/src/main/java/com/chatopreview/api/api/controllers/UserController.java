package com.chatopreview.api.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.repository.UserRepository;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<String> info(){
        return ResponseEntity.ok("Bonjour, user");
    }
        @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}

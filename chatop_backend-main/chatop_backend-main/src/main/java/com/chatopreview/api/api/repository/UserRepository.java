package com.chatopreview.api.api.repository;

import com.chatopreview.api.api.entities.Role;
import com.chatopreview.api.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}

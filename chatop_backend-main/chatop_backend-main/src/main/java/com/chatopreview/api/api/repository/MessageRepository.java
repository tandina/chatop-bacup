package com.chatopreview.api.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chatopreview.api.api.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
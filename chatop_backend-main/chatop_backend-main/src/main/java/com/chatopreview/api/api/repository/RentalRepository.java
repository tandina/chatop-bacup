package com.chatopreview.api.api.repository;

import com.chatopreview.api.api.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}

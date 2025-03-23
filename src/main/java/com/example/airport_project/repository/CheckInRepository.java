package com.example.airport_project.repository;

import com.example.airport_project.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    boolean existsByBookingId(Long bookingId);
}

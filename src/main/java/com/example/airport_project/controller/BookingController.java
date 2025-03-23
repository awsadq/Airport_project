package com.example.airport_project.controller;

import com.example.airport_project.service.BookingService;
import com.example.airport_project.web.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO, Principal principal) {
        Long bookingId = bookingService.createBookingAndReturnId(bookingDTO, principal.getName());
        return ResponseEntity.ok(bookingId);
    }
}

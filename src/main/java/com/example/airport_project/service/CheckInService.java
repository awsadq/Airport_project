package com.example.airport_project.service;

import com.example.airport_project.model.Booking;
import com.example.airport_project.model.CheckIn;
import com.example.airport_project.repository.BookingRepository;
import com.example.airport_project.repository.CheckInRepository;
import com.example.airport_project.web.dto.CheckInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void checkIn(CheckInDTO dto) {
        if (checkInRepository.existsByBookingId(dto.getBookingId())) {
            throw new RuntimeException("Пассажир уже зарегистрирован на рейс");
        }

        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Бронирование не найдено"));

        CheckIn checkIn = new CheckIn();
        checkIn.setBooking(booking);
        checkIn.setBaggageStatus(dto.getBaggageStatus() != null ? dto.getBaggageStatus() : false);
        checkIn.setBoardingPass(generateBoardingPass());

        checkInRepository.save(checkIn);
    }

    private String generateBoardingPass() {
        return "BP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

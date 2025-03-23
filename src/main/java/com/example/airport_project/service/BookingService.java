package com.example.airport_project.service;

import com.example.airport_project.model.Booking;
import com.example.airport_project.model.Flight;
import com.example.airport_project.model.User;
import com.example.airport_project.repository.BookingRepository;
import com.example.airport_project.repository.FlightRepository;
import com.example.airport_project.repository.UserRepository;
import com.example.airport_project.web.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Long createBookingAndReturnId(BookingDTO dto, String email) {
        try {
            System.out.println("📥 Полученные данные от клиента: " + dto);

            if (dto.getFlightId() == null || dto.getFlightId() <= 0) {
                throw new RuntimeException("🔥 Ошибка: Flight ID не должен быть null или <= 0");
            }

            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new RuntimeException("❌ Ошибка: Пользователь с email " + email + " не найден");
            }

            Flight flight = flightRepository.findById(dto.getFlightId())
                    .orElseThrow(() -> new RuntimeException("❌ Ошибка: Рейс не найден"));

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setFlight(flight);
            booking.setPassengerName(dto.getPassengerName().trim());
            booking.setPassportNumber(dto.getPassportNumber().trim());
            booking.setSeatNumber(dto.getSeatNumber().trim());
            booking.setBookingStatus("Забронирован");

            bookingRepository.save(booking);

            return booking.getId(); // возвращаем ID
        } catch (Exception e) {
            System.err.println("❌ Ошибка при бронировании: " + e.getMessage());
            throw new RuntimeException("Ошибка при бронировании: " + e.getMessage());
        }
    }

}

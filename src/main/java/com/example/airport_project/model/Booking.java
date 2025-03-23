package com.example.airport_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // 👉 Геттеры и сеттеры
    public void setUser(User user) {
        this.user = user;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }
}

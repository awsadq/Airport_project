package com.example.airport_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime = LocalDateTime.now();

    @Column(name = "boarding_pass", unique = true, nullable = false)
    private String boardingPass;

    @Column(name = "baggage_status")
    private Boolean baggageStatus = false;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(String boardingPass) {
        this.boardingPass = boardingPass;
    }

    public Boolean getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(Boolean baggageStatus) {
        this.baggageStatus = baggageStatus;
    }

}

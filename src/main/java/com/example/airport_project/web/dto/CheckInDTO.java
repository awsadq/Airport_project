package com.example.airport_project.web.dto;

public class CheckInDTO {

    private Long bookingId;
    private Boolean baggageStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Boolean getBaggageStatus() {
        return baggageStatus;
    }

    public void setBaggageStatus(Boolean baggageStatus) {
        this.baggageStatus = baggageStatus;
    }
}

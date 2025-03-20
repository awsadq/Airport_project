package com.example.airport_project.repository;

import com.example.airport_project.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByArrivalAirportContainingIgnoreCase(String arrivalAirport);
    List<Flight> findByDepartureAirportContainingIgnoreCase(String departureAirport);
    List<Flight> findByStatusContainingIgnoreCase(String status);
    List<Flight> findByFlightNumberContainingIgnoreCase(String flightNumber);

    // Поиск по всем критериям
    List<Flight> findByDepartureAirportContainingIgnoreCaseAndArrivalAirportContainingIgnoreCaseAndStatusContainingIgnoreCase(
            String departureAirport, String arrivalAirport, String status);
}

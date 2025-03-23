package com.example.airport_project.service;

import com.example.airport_project.model.Flight;
import com.example.airport_project.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Загрузка всех рейсов
    public List<Flight> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        System.out.println("Полученные рейсы: " + flights);
        return flights;
    }

    // Поиск по аэропорту отправления
    public List<Flight> searchByDepartureAirport(String departureAirport) {
        return flightRepository.findByDepartureAirportContainingIgnoreCase(departureAirport);
    }

    // Поиск по аэропорту прибытия
    public List<Flight> searchByArrivalAirport(String arrivalAirport) {
        return flightRepository.findByArrivalAirportContainingIgnoreCase(arrivalAirport);
    }

    // Поиск по статусу
    public List<Flight> searchByStatus(String status) {
        return flightRepository.findByStatusContainingIgnoreCase(status);
    }

    // Поиск по всем критериям
    public List<Flight> searchByAllCriteria(String departureAirport, String arrivalAirport, String status) {
        return flightRepository.findByDepartureAirportContainingIgnoreCaseAndArrivalAirportContainingIgnoreCaseAndStatusContainingIgnoreCase(
                departureAirport, arrivalAirport, status);
    }


}

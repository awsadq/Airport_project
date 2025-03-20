package com.example.airport_project.controller;

import com.example.airport_project.model.Flight;
import com.example.airport_project.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    // Загрузка всех рейсов при инициализации страницы
    @GetMapping
    public List<Flight> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        logger.info("Рейсы: {}", flights);
        if (flights.isEmpty()) {
            logger.info("Нет рейсов в базе данных.");
        }
        return flights;
    }

    // Поиск рейсов с фильтрацией по нескольким параметрам
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam(value = "departureAirport", required = false) String departureAirport,
            @RequestParam(value = "arrivalAirport", required = false) String arrivalAirport,
            @RequestParam(value = "status", required = false) String status
    ) {
        logger.info("Поиск рейсов с параметрами: departureAirport={}, arrivalAirport={}, status={}", departureAirport, arrivalAirport, status);

        // Если все параметры равны null или "ничего", вернуть все рейсы
        if ((departureAirport == null || "ничего".equals(departureAirport)) &&
                (arrivalAirport == null || "ничего".equals(arrivalAirport)) &&
                (status == null || "ничего".equals(status))) {
            return flightService.getAllFlights();
        }

        // Поиск по всем заданным критериям
        if (departureAirport != null && !departureAirport.isEmpty() &&
                arrivalAirport != null && !arrivalAirport.isEmpty() &&
                status != null && !status.isEmpty()) {
            return flightService.searchByAllCriteria(departureAirport, arrivalAirport, status);
        }

        // Поиск по одному или нескольким критериям
        if (departureAirport != null && !departureAirport.isEmpty() && !"ничего".equals(departureAirport)) {
            return flightService.searchByDepartureAirport(departureAirport);
        } else if (arrivalAirport != null && !arrivalAirport.isEmpty() && !"ничего".equals(arrivalAirport)) {
            return flightService.searchByArrivalAirport(arrivalAirport);
        } else if (status != null && !status.isEmpty() && !"ничего".equals(status)) {
            return flightService.searchByStatus(status);
        }

        return flightService.getAllFlights();
    }
}

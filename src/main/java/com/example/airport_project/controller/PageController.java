package com.example.airport_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/flights")
    public String showFlightsPage() {
        return "flights";  // Это будет имя шаблона, который вы создали для страницы рейсов (flights.html)
    }
}

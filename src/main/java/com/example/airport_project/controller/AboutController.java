package com.example.airport_project.controller;

import com.example.airport_project.model.AboutCard;
import com.example.airport_project.repository.AboutCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {

    private final AboutCardRepository aboutCardRepository;

    @Autowired
    public AboutController(AboutCardRepository aboutCardRepository) {
        this.aboutCardRepository = aboutCardRepository;
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        List<AboutCard> cards = aboutCardRepository.findAll();
        model.addAttribute("cards", cards);
        return "about";
    }
}

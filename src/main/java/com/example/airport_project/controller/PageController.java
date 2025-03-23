package com.example.airport_project.controller;

import com.example.airport_project.model.News;
import com.example.airport_project.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class PageController {

    private final NewsRepository newsRepository;

    @Autowired
    public PageController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/flights")
    public String showFlightsPage() {
        return "flights";
    }

    @GetMapping("/booking")
    public String bookingPage() {
        return "redirect:/booking.html";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<News> newsList = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "publishedAt"));
        List<Map<String, String>> formattedNews = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));

        for (News news : newsList) {
            Map<String, String> item = new HashMap<>();
            item.put("title", news.getTitle());
            item.put("description", news.getDescription());
            item.put("imageUrl", news.getImageUrl());
            item.put("publishedAt", news.getPublishedAt().format(formatter));
            formattedNews.add(item);
        }

        model.addAttribute("news", formattedNews);
        return "index";
    }
}

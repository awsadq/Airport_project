package com.example.airport_project.controller;

import com.example.airport_project.model.CheckIn;
import com.example.airport_project.repository.CheckInRepository;
import com.example.airport_project.service.CheckInService;
import com.example.airport_project.util.BoardingPassGenerator;
import com.example.airport_project.web.dto.CheckInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/check-in")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private BoardingPassGenerator boardingPassGenerator;

    // 📌 Регистрация на рейс
    @PostMapping
    public ResponseEntity<?> checkIn(@RequestBody CheckInDTO dto) {
        try {
            checkInService.checkIn(dto);
            return ResponseEntity.ok("✅ Регистрация на рейс успешно выполнена");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("❌ Ошибка: " + e.getMessage());
        }
    }

    // 📄 Получение PDF посадочного талона
    @GetMapping("/{bookingId}/boarding-pass")
    public ResponseEntity<byte[]> getBoardingPass(@PathVariable Long bookingId) {
        CheckIn checkIn = checkInRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Регистрация не найдена"));

        byte[] pdf = boardingPassGenerator.generatePdf(checkIn);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("boarding-pass.pdf")
                .build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

}

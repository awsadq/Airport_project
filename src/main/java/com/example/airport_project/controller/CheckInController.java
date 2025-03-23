package com.example.airport_project.controller;

import com.example.airport_project.model.CheckIn;
import com.example.airport_project.repository.CheckInRepository;
import com.example.airport_project.service.CheckInService;
import com.example.airport_project.util.BoardingPassGenerator;
import com.example.airport_project.web.dto.CheckInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}/boarding-pass")
    public ResponseEntity<byte[]> getBoardingPass(@PathVariable Long id) {
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Регистрация не найдена"));

        byte[] pdf = boardingPassGenerator.generatePdf(checkIn);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=boarding-pass.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}

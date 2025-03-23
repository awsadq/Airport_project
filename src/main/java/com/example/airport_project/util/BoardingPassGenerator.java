package com.example.airport_project.util;

import com.example.airport_project.model.CheckIn;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class BoardingPassGenerator {

    public byte[] generatePdf(CheckIn checkIn) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        try {
            // Подключаем стандартный шрифт, поддерживающий кириллицу (например, Times-Roman)
            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            document.setFont(font);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            document.add(new Paragraph("Посадочный талон").setBold().setFontSize(18).setMarginBottom(12));

            document.add(new Paragraph("Имя пассажира: " + checkIn.getBooking().getPassengerName()));
            document.add(new Paragraph("Номер паспорта: " + checkIn.getBooking().getPassportNumber()));
            document.add(new Paragraph("Номер рейса: " + checkIn.getBooking().getFlight().getFlightNumber()));
            document.add(new Paragraph("Аэропорт вылета: " + checkIn.getBooking().getFlight().getDepartureAirport()));
            document.add(new Paragraph("Аэропорт прибытия: " + checkIn.getBooking().getFlight().getArrivalAirport()));
            document.add(new Paragraph("Место: " + checkIn.getBooking().getSeatNumber()));
            document.add(new Paragraph("Посадочный номер: " + checkIn.getBoardingPass()));
            document.add(new Paragraph("Дата регистрации: " + checkIn.getCheckInTime().format(formatter)));

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при генерации PDF: " + e.getMessage());
        }

        return out.toByteArray();
    }
}

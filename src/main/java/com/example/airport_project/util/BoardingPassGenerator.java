package com.example.airport_project.util;

import com.example.airport_project.model.CheckIn;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class BoardingPassGenerator {

    public byte[] generatePdf(CheckIn checkIn) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Используем стандартный бесплатный шрифт
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            // 🛫 Заголовок
            document.add(new Paragraph("🛫 BOARDING PASS")
                    .setBold()
                    .setFontSize(20)
                    .setMarginBottom(15));

            // 🧾 Таблица с иконками
            float[] columnWidths = {180F, 300F};
            Table table = new Table(columnWidths);

            table.addCell(new Paragraph("👤 Passenger Name").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getPassengerName()));

            table.addCell(new Paragraph("🪪 Passport Number").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getPassportNumber()));

            table.addCell(new Paragraph("✈️ Flight Number").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getFlight().getFlightNumber()));

            table.addCell(new Paragraph("🛫 Departure Airport").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getFlight().getDepartureAirport()));

            table.addCell(new Paragraph("🛬 Arrival Airport").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getFlight().getArrivalAirport()));

            table.addCell(new Paragraph("💺 Seat").setBold());
            table.addCell(new Paragraph(checkIn.getBooking().getSeatNumber()));

            table.addCell(new Paragraph("📄 Boarding Code").setBold());
            table.addCell(new Paragraph(checkIn.getBoardingPass()));

            table.addCell(new Paragraph("🕒 Check-In Time").setBold());
            table.addCell(new Paragraph(checkIn.getCheckInTime().format(formatter)));

            document.add(table);

            // ✅ Завершение
            document.add(new Paragraph("\nHave a nice flight! ✈️")
                    .setItalic()
                    .setFontSize(12)
                    .setMarginTop(10));

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при генерации PDF: " + e.getMessage(), e);
        }

        return out.toByteArray();
    }
}

package com.location.controller;

import com.location.entity.Paiement;
import com.location.service.PaiementService;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

@WebServlet("/paiements/receipt-pdf")
public class ReceiptPdfServlet extends HttpServlet {

    private final PaiementService paiementService = new PaiementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Paiement p = paiementService.findById(id);

        if (p == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Paiement introuvable");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=recu-" + p.getId() + ".pdf");

        Document document = new Document(PageSize.A4, 40, 40, 50, 40);

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, new Color(17, 24, 39));
            Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRANCE);
            nf.setMaximumFractionDigits(0);

            Paragraph title = new Paragraph("REÇU DE PAIEMENT", titleFont);
            title.setSpacingAfter(20f);
            document.add(title);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            addRow(table, "Référence", String.valueOf(p.getId()), labelFont, valueFont);
            addRow(table, "Date", new SimpleDateFormat("dd/MM/yyyy").format(p.getDatePaiement()), labelFont, valueFont);
            addRow(table, "Montant", nf.format(p.getMontant()) + " FCFA", labelFont, valueFont);
            addRow(table, "Mode", p.getModePaiement(), labelFont, valueFont);
            addRow(table, "Statut", p.getStatut(), labelFont, valueFont);

            document.add(table);

            Paragraph footer = new Paragraph("Merci pour votre paiement.", valueFont);
            footer.setSpacingBefore(20f);
            document.add(footer);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la génération du PDF");
        } finally {
            document.close();
        }
    }

    private void addRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell cell1 = new PdfPCell(new Phrase(label, labelFont));
        cell1.setPadding(8f);
        cell1.setBackgroundColor(new Color(239, 246, 255));
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(value, valueFont));
        cell2.setPadding(8f);
        table.addCell(cell2);
    }
}
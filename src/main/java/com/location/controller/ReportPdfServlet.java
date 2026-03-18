package com.location.controller;

import com.location.service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet("/reports/pdf")
public class ReportPdfServlet extends HttpServlet {

    private final ReportService reportService = new ReportService();

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=rapport-statistiques.pdf");

        long nbDemandes = reportService.getDemandesCount();
        long nbContrats = reportService.getContratsCount();
        long nbPaiements = reportService.getPaiementsCount();
        double totalPaiements = reportService.getTotalPaiements();

        Document document = new Document(PageSize.A4, 40, 40, 50, 40);

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, new Color(17, 24, 39));
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA, 11, new Color(107, 114, 128));
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.WHITE);
            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12, new Color(31, 41, 55));
            Font strongFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new Color(17, 24, 39));

            Paragraph appName = new Paragraph("Gestion Locations", strongFont);
            appName.setSpacingAfter(8f);
            document.add(appName);

            Paragraph title = new Paragraph("Rapport des statistiques", titleFont);
            title.setSpacingAfter(6f);
            document.add(title);

            String dateEdition = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
            Paragraph subtitle = new Paragraph("Date d'édition : " + dateEdition, subtitleFont);
            subtitle.setSpacingAfter(20f);
            document.add(subtitle);

            PdfPTable summaryTable = new PdfPTable(2);
            summaryTable.setWidthPercentage(100);
            summaryTable.setSpacingBefore(10f);
            summaryTable.setSpacingAfter(10f);
            summaryTable.setWidths(new float[]{3f, 2f});

            addHeaderCell(summaryTable, "Indicateur", sectionFont);
            addHeaderCell(summaryTable, "Valeur", sectionFont);

            addDataCell(summaryTable, "Demandes", cellFont);
            addDataCell(summaryTable, String.valueOf(nbDemandes), cellFont);

            addDataCell(summaryTable, "Contrats", cellFont);
            addDataCell(summaryTable, String.valueOf(nbContrats), cellFont);

            addDataCell(summaryTable, "Paiements", cellFont);
            addDataCell(summaryTable, String.valueOf(nbPaiements), cellFont);

            NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRANCE);
            nf.setMaximumFractionDigits(0);

            addDataCell(summaryTable, "Total encaissé", strongFont);
            addDataCell(summaryTable, nf.format(totalPaiements) + " FCFA", strongFont);

            document.add(summaryTable);

            Paragraph note = new Paragraph(
                    "Ce document présente une synthèse des activités et indicateurs financiers du système.",
                    subtitleFont
            );
            note.setSpacingBefore(14f);
            document.add(note);

        } catch (DocumentException e) {
            throw new ServletException("Erreur lors de la génération du PDF.", e);
        } finally {
            document.close();
        }
    }

    private void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new Color(37, 99, 235));
        cell.setPadding(10f);
        cell.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell);
    }

    private void addDataCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(10f);
        cell.setBorderColor(new Color(229, 231, 235));
        cell.setBorderWidth(1f);
        cell.setBackgroundColor(Color.WHITE);
        table.addCell(cell);
    }
}
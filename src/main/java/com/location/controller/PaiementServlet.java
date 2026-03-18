package com.location.controller;

import com.location.entity.ContratLocation;
import com.location.entity.Paiement;
import com.location.service.ContratService;
import com.location.service.PaiementService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@WebServlet("/paiements")
public class PaiementServlet extends HttpServlet {
    private final PaiementService service = new PaiementService();
    private final ContratService contratService = new ContratService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");

        if ("delete".equals(action) && id != null) {
            service.delete(id);
            response.sendRedirect(request.getContextPath() + "/paiements");
            return;
        }

        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id));
        }

        request.setAttribute("items", service.findAll());
        request.setAttribute("contrats", contratService.findAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/paiements/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = BaseCrudServletHelper.parseId(request, "id");
            Integer contratId = BaseCrudServletHelper.parseId(request, "contratId");

            ContratLocation contrat = contratService.findById(contratId);
            Date today = new Date();

            if (contrat != null && contrat.getDateFin() != null && contrat.getDateFin().before(today)) {
                request.setAttribute("erreur", "Le contrat est expiré. Paiement impossible.");
                doGet(request, response);
                return;
            }

            Paiement item = (id != null) ? service.findById(id) : new Paiement();

            Double montant = Double.valueOf(request.getParameter("montant"));
            String modePaiement = request.getParameter("modePaiement");

            Calendar cal = Calendar.getInstance();
            cal.setTime(today);

            int mois = cal.get(Calendar.MONTH) + 1;
            int annee = cal.get(Calendar.YEAR);

            cal.set(Calendar.DAY_OF_MONTH, 5);
            Date dateEcheance = cal.getTime();

            double montantAttendu = 0.0;
            if (contrat != null && contrat.getUniteLocation() != null && contrat.getUniteLocation().getLoyer() != null) {
                montantAttendu = contrat.getUniteLocation().getLoyer();
            }

            String statut;
            if (montant >= montantAttendu) {
                statut = "PAYE";
            } else if (montant > 0) {
                if (today.after(dateEcheance)) {
                    statut = "EN_RETARD";
                } else {
                    statut = "PARTIEL";
                }
            } else {
                statut = "EN_ATTENTE";
            }

            item.setContrat(contrat);
            item.setDatePaiement(today);
            item.setMontant(montant);
            item.setModePaiement(modePaiement);
            item.setMoisPaiement(mois);
            item.setAnneePaiement(annee);
            item.setDateEcheance(dateEcheance);
            item.setStatut(statut);

            if (item.getNumeroRecu() == null || item.getNumeroRecu().isBlank()) {
                item.setNumeroRecu("REC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            }

            service.save(item);
            response.sendRedirect(request.getContextPath() + "/paiements");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
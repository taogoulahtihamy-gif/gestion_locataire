package com.location.controller;

import com.location.entity.ContratLocation;
import com.location.entity.DemandeLocation;
import com.location.entity.Locataire;
import com.location.entity.UniteLocation;
import com.location.service.ContratService;
import com.location.service.DemandeService;
import com.location.service.LocataireService;
import com.location.service.UniteLocationService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/contrats")
public class ContratServlet extends HttpServlet {
    private final ContratService service = new ContratService();
    private final LocataireService locataireService = new LocataireService();
    private final UniteLocationService uniteService = new UniteLocationService();
    private final DemandeService demandeService = new DemandeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");

        if ("delete".equals(action) && id != null) {
            service.delete(id);
            response.sendRedirect(request.getContextPath() + "/contrats");
            return;
        }

        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id));
        }

        // ✅ pré-remplissage depuis une demande acceptée
        if ("fromDemande".equals(action) && request.getParameter("demandeId") != null) {
            System.out.println("demandeId reçu = " + request.getParameter("demandeId"));

            Long demandeId = Long.valueOf(request.getParameter("demandeId"));
            System.out.println("demandeSourceId envoyé au JSP = " + demandeId);

            DemandeLocation demande = demandeService.findById(demandeId);

            if (demande != null && "ACCEPTEE".equalsIgnoreCase(demande.getStatut())) {
                ContratLocation item = new ContratLocation();
                item.setLocataire(demande.getLocataire());
                item.setUniteLocation(demande.getUniteLocation());
                item.setStatut("Actif");
                request.setAttribute("item", item);
                request.setAttribute("demandeSourceId", demandeId);
            }
        }

        request.setAttribute("items", service.findAll());
        request.setAttribute("locataires", locataireService.findAll());
        request.setAttribute("unites", uniteService.findAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/contrats/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ContratLocation item = new ContratLocation();
            Integer id = BaseCrudServletHelper.parseId(request, "id");
            item.setId(id);

            Locataire locataire = locataireService.findById(
                    BaseCrudServletHelper.parseId(request, "locataireId")
            );

            UniteLocation unite = uniteService.findById(
                    BaseCrudServletHelper.parseId(request, "uniteId")
            );

            String demandeSourceId = request.getParameter("demandeSourceId");

            if (unite != null
                    && "Occupée".equalsIgnoreCase(unite.getStatut())
                    && id == null
                    && (demandeSourceId == null || demandeSourceId.isBlank())) {

                request.setAttribute("errorMessage", "Cette unité est déjà occupée. Veuillez en choisir une autre.");
                request.setAttribute("item", item);
                request.setAttribute("items", service.findAll());
                request.setAttribute("locataires", locataireService.findAll());
                request.setAttribute("unites", uniteService.findAll());

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/contrats/index.jsp");
                dispatcher.forward(request, response);
                return;
            }

            item.setLocataire(locataire);
            item.setUniteLocation(unite);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            item.setDateDebut(sdf.parse(request.getParameter("dateDebut")));
            item.setDateFin(sdf.parse(request.getParameter("dateFin")));
            item.setStatut(request.getParameter("statut"));

            service.save(item);

            if (unite != null) {
                unite.setStatut("Occupée");
                uniteService.save(unite);
            }

            if (demandeSourceId != null && !demandeSourceId.isBlank()) {
                DemandeLocation demande = demandeService.findById(Long.valueOf(demandeSourceId));
                if (demande != null) {
                    demande.setStatut("CONTRAT_CREE");
                    demandeService.save(demande);
                }
            }

            response.sendRedirect(request.getContextPath() + "/contrats");

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement du contrat : " + e.getMessage());
            request.setAttribute("items", service.findAll());
            request.setAttribute("locataires", locataireService.findAll());
            request.setAttribute("unites", uniteService.findAll());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/contrats/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
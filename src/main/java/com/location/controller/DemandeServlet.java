package com.location.controller;

import com.location.entity.DemandeLocation;
import com.location.entity.Locataire;
import com.location.entity.UniteLocation;
import com.location.service.DemandeService;
import com.location.service.LocataireService;
import com.location.service.UniteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/demandes")
public class DemandeServlet extends HttpServlet {

    private final DemandeService service = new DemandeService();
    private final LocataireService locataireService = new LocataireService();
    private final UniteService uniteService = new UniteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String idParam = req.getParameter("id");

        if ("delete".equals(action) && idParam != null) {
            service.delete(Long.valueOf(idParam));
            resp.sendRedirect(req.getContextPath() + "/demandes");
            return;
        }

        if ("edit".equals(action) && idParam != null) {
            req.setAttribute("item", service.findById(Long.valueOf(idParam)));
        }

        if ("accepter".equals(action) && idParam != null) {
            DemandeLocation demande = service.findById(Long.valueOf(idParam));
            if (demande != null) {
                demande.setStatut("ACCEPTEE");
                service.save(demande);
            }
            resp.sendRedirect(req.getContextPath() + "/demandes");
            return;
        }

        if ("refuser".equals(action) && idParam != null) {
            DemandeLocation demande = service.findById(Long.valueOf(idParam));
            if (demande != null) {
                demande.setStatut("REFUSEE");
                service.save(demande);
            }
            resp.sendRedirect(req.getContextPath() + "/demandes");
            return;
        }

        List<DemandeLocation> items = service.findAll();

        req.setAttribute("items", items);
        req.setAttribute("locataires", locataireService.findAll());
        req.setAttribute("unites", uniteService.disponibles());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/demandes/index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DemandeLocation d = (req.getParameter("id") == null || req.getParameter("id").isBlank())
                    ? new DemandeLocation()
                    : service.findById(Long.valueOf(req.getParameter("id")));

            Locataire l = locataireService.findById(Integer.valueOf(req.getParameter("locataireId")));
            UniteLocation u = uniteService.findById(Long.valueOf(req.getParameter("uniteId")));

            d.setLocataire(l);
            d.setUniteLocation(u);
            d.setDateDemande(new Date());
            d.setStatut(req.getParameter("statut"));
            d.setCommentaire(req.getParameter("commentaire"));

            service.save(d);
            resp.sendRedirect(req.getContextPath() + "/demandes");

        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'enregistrement de la demande", e);
        }
    }
}
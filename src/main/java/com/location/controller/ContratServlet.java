package com.location.controller;

import com.location.entity.ContratLocation;
import com.location.entity.Locataire;
import com.location.entity.UniteLocation;
import com.location.service.ContratService;
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
            Locataire locataire = locataireService.findById(BaseCrudServletHelper.parseId(request, "locataireId"));
            UniteLocation unite = uniteService.findById(BaseCrudServletHelper.parseId(request, "uniteId"));
            if (unite != null && "Occupée".equalsIgnoreCase(unite.getStatut()) && id == null) {
                throw new ServletException("Cette unité est déjà occupée.");
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
            response.sendRedirect(request.getContextPath() + "/contrats");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

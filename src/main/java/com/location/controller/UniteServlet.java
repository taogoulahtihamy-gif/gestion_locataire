package com.location.controller;

import com.location.entity.Immeuble;
import com.location.entity.UniteLocation;
import com.location.service.ImmeubleService;
import com.location.service.UniteService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/unites")
public class UniteServlet extends HttpServlet {

    private final UniteService service = new UniteService();
    private final ImmeubleService immeubleService = new ImmeubleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");

        if ("delete".equals(action) && id != null) {
            service.delete(id.longValue());
            response.sendRedirect(request.getContextPath() + "/unites");
            return;
        }

        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id.longValue()));
        }

        request.setAttribute("items", service.disponibles());
        request.setAttribute("immeubles", immeubleService.findAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/unites/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Integer id = BaseCrudServletHelper.parseId(request, "id");

            UniteLocation item = (id != null)
                    ? service.findById(id.longValue())
                    : new UniteLocation();

            Integer immeubleId = Integer.valueOf(request.getParameter("immeubleId"));
            Immeuble immeuble = immeubleService.findById(immeubleId);

            item.setImmeuble(immeuble);
            item.setNombrePieces(Integer.parseInt(request.getParameter("nombrePieces")));
            item.setSuperficie(Double.parseDouble(request.getParameter("superficie")));
            item.setLoyer(Double.parseDouble(request.getParameter("loyer")));
            item.setStatut(request.getParameter("statut"));

            service.save(item);

            response.sendRedirect(request.getContextPath() + "/unites");

        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'enregistrement de l'unité", e);
        }
    }
}
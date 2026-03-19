package com.location.controller;

import com.location.service.DemandeLocationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/demandes/action")
public class DemandeActionServlet extends HttpServlet {

    private final DemandeLocationService demandeService = new DemandeLocationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if (idStr == null || idStr.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/demandes");
            return;
        }

        Long id = Long.valueOf(idStr);

        if ("accepter".equalsIgnoreCase(action)) {
            demandeService.updateStatut(id, "ACCEPTEE");
        } else if ("refuser".equalsIgnoreCase(action)) {
            demandeService.updateStatut(id, "REFUSEE");
        }

        resp.sendRedirect(req.getContextPath() + "/demandes");
    }
}
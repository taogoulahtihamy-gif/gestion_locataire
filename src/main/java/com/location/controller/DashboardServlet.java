package com.location.controller;

import com.location.service.DashboardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private final DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nbImmeubles", dashboardService.getImmeublesCount());
        request.setAttribute("nbUnites", dashboardService.getUnitesCount());
        request.setAttribute("nbLocataires", dashboardService.getLocatairesCount());
        request.setAttribute("nbContrats", dashboardService.getContratsCount());
        request.setAttribute("nbPaiements", dashboardService.getPaiementsCount());
        request.setAttribute("nbUtilisateurs", dashboardService.getUtilisateursCount());
        request.setAttribute("totalPaiements", dashboardService.getTotalPaiements());

        request.setAttribute("nbContratsActifs", dashboardService.getContratsActifsCount());
        request.setAttribute("nbUnitesDisponibles", dashboardService.getUnitesDisponiblesCount());
        request.setAttribute("nbUnitesOccupees", dashboardService.getUnitesOccupeesCount());
        request.setAttribute("nbPaiementsEnRetard", dashboardService.getPaiementsEnRetardCount());
        request.setAttribute("tauxOccupation", dashboardService.getTauxOccupation());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
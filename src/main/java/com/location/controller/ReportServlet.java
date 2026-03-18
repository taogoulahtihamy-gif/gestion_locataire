package com.location.controller;

import com.location.service.DashboardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {
    private final DashboardService dashboardService = new DashboardService();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("totalPaiements", dashboardService.totalPaiements());
        req.setAttribute("nbContrats", dashboardService.count("ContratLocation"));
        req.setAttribute("nbPaiements", dashboardService.count("Paiement"));
        req.setAttribute("nbDemandes", dashboardService.count("DemandeLocation"));
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(req, resp);
    }
}

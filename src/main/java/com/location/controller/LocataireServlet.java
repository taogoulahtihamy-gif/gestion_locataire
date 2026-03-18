package com.location.controller;

import com.location.entity.Locataire;
import com.location.service.LocataireService;
import com.location.service.UniteLocationService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/locataires")
public class LocataireServlet extends HttpServlet {
    private final LocataireService service = new LocataireService();
    private final UniteLocationService uniteService = new UniteLocationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");
        if ("delete".equals(action) && id != null) {
            service.delete(id);
            response.sendRedirect(request.getContextPath() + "/locataires");
            return;
        }
        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id));
        }
        request.setAttribute("items", service.findAll());
        request.setAttribute("offres", uniteService.getDisponibles());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/locataires/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Locataire item = new Locataire();
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        item.setId(id);
        item.setNom(request.getParameter("nom"));
        item.setEmail(request.getParameter("email"));
        item.setTelephone(request.getParameter("telephone"));
        item.setAdresse(request.getParameter("adresse"));
        service.save(item);
        response.sendRedirect(request.getContextPath() + "/locataires");
    }
}

package com.location.controller;

import com.location.entity.Utilisateur;
import com.location.service.UtilisateurService;
import com.location.util.PasswordUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/utilisateurs")
public class UtilisateurServlet extends HttpServlet {
    private final UtilisateurService service = new UtilisateurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");
        if ("delete".equals(action) && id != null) {
            service.delete(id);
            response.sendRedirect(request.getContextPath() + "/utilisateurs");
            return;
        }
        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id));
        }
        request.setAttribute("items", service.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/utilisateurs/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utilisateur item = new Utilisateur();
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        item.setId(id);
        item.setNom(request.getParameter("nom"));
        item.setEmail(request.getParameter("email"));
        item.setRole(request.getParameter("role"));
        String rawPassword = request.getParameter("motDePasse");
        if (id != null && (rawPassword == null || rawPassword.isBlank())) {
            Utilisateur existing = service.findById(id);
            item.setMotDePasse(existing != null ? existing.getMotDePasse() : PasswordUtil.hash("admin123"));
        } else {
            item.setMotDePasse(PasswordUtil.hash(rawPassword));
        }
        service.save(item);
        response.sendRedirect(request.getContextPath() + "/utilisateurs");
    }
}

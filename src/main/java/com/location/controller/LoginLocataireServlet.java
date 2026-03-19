package com.location.controller;

import com.location.entity.Locataire;
import com.location.util.JpaUtil;
import com.location.util.PasswordUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login-locataire")
public class LoginLocataireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/public/login-locataire.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String email = req.getParameter("email");
            String motDePasse = req.getParameter("motDePasse");

            Locataire locataire = em.createQuery(
                            "select l from Locataire l where lower(l.email) = :email", Locataire.class)
                    .setParameter("email", email.toLowerCase())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (locataire != null && PasswordUtil.verify(motDePasse, locataire.getMotDePasse())) {
                HttpSession session = req.getSession(true);
                session.setAttribute("locataireSession", locataire);
                resp.sendRedirect(req.getContextPath() + "/profil-locataire");
                return;
            }

            req.setAttribute("error", "Email ou mot de passe incorrect.");
            req.getRequestDispatcher("/WEB-INF/views/public/login-locataire.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }
}
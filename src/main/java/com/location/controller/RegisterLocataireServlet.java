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

import java.io.IOException;

@WebServlet("/register")
public class RegisterLocataireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/public/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String nom = req.getParameter("nom");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");
            String adresse = req.getParameter("adresse");
            String motDePasse = req.getParameter("motDePasse");

            Long count = em.createQuery(
                            "select count(l) from Locataire l where lower(l.email) = :email", Long.class)
                    .setParameter("email", email.toLowerCase())
                    .getSingleResult();

            if (count != null && count > 0) {
                req.setAttribute("error", "Un compte locataire existe déjà avec cet email.");
                req.getRequestDispatcher("/WEB-INF/views/public/register.jsp").forward(req, resp);
                return;
            }

            em.getTransaction().begin();

            Locataire l = new Locataire();
            l.setNom(nom);
            l.setEmail(email);
            l.setTelephone(telephone);
            l.setAdresse(adresse);
            l.setMotDePasse(PasswordUtil.hash(motDePasse));

            em.persist(l);
            em.getTransaction().commit();

            resp.sendRedirect(req.getContextPath() + "/login-locataire");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }
}
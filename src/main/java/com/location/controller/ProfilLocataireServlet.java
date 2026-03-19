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

@WebServlet("/profil-locataire")
public class ProfilLocataireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/public/profil-locataire.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Locataire current = (Locataire) session.getAttribute("locataireSession");

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Locataire l = em.find(Locataire.class, current.getId());

            l.setNom(req.getParameter("nom"));
            l.setTelephone(req.getParameter("telephone"));
            l.setAdresse(req.getParameter("adresse"));

            String nouveauMdp = req.getParameter("motDePasse");
            if (nouveauMdp != null && !nouveauMdp.isBlank()) {
                l.setMotDePasse(PasswordUtil.hash(nouveauMdp));
            }

            em.getTransaction().commit();

            session.setAttribute("locataireSession", l);
            req.setAttribute("success", "Profil mis à jour avec succès.");
            req.getRequestDispatcher("/WEB-INF/views/public/profil-locataire.jsp").forward(req, resp);

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }
}
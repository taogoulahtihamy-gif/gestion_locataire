package com.location.controller;

import com.location.entity.DemandeLocation;
import com.location.entity.Locataire;
import com.location.entity.UniteLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/demande-location")
public class DemandeLocationPublicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Locataire locataire = session != null ? (Locataire) session.getAttribute("locataireSession") : null;

        if (locataire == null) {
            resp.sendRedirect(req.getContextPath() + "/login-locataire");
            return;
        }

        EntityManager em = JpaUtil.getEntityManager();
        try {
            Integer uniteId = Integer.valueOf(req.getParameter("uniteId"));
            String commentaire = req.getParameter("commentaire");

            em.getTransaction().begin();

            Locataire managedLocataire = em.find(Locataire.class, locataire.getId());
            UniteLocation unite = em.find(UniteLocation.class, uniteId);

            DemandeLocation demande = new DemandeLocation();
            demande.setLocataire(managedLocataire);
            demande.setUniteLocation(unite);
            demande.setDateDemande(new Date());
            demande.setStatut("EN_ATTENTE");
            demande.setCommentaire(commentaire);

            em.persist(demande);
            em.getTransaction().commit();

            resp.sendRedirect(req.getContextPath() + "/mes-demandes");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new ServletException(e);
        } finally {
            em.close();
        }
    }
}
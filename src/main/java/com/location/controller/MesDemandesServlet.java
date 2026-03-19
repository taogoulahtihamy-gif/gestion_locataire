package com.location.controller;

import com.location.entity.DemandeLocation;
import com.location.entity.Locataire;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/mes-demandes")
public class MesDemandesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Locataire locataire = (Locataire) session.getAttribute("locataireSession");

        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<DemandeLocation> demandes = em.createQuery(
                            "select d from DemandeLocation d where d.locataire.id = :id order by d.dateDemande desc",
                            DemandeLocation.class)
                    .setParameter("id", locataire.getId())
                    .getResultList();

            req.setAttribute("demandes", demandes);
            req.getRequestDispatcher("/WEB-INF/views/public/mes-demandes.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }
}
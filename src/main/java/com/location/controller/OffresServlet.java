package com.location.controller;

import com.location.entity.UniteLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/offres")
public class OffresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<UniteLocation> offres = em.createQuery(
                            "select u from UniteLocation u where lower(u.statut) = 'disponible'", UniteLocation.class)
                    .getResultList();

            req.setAttribute("offres", offres);
            req.getRequestDispatcher("/WEB-INF/views/public/offres.jsp").forward(req, resp);
        } finally {
            em.close();
        }
    }
}
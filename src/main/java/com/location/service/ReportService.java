package com.location.service;

import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class ReportService {

    public long count(String entityName) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            String jpql = "select count(e) from " + entityName + " e";
            return em.createQuery(jpql, Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    public double totalPaiements() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Double total = em.createQuery(
                    "select coalesce(sum(p.montant), 0) from Paiement p",
                    Double.class
            ).getSingleResult();
            return total != null ? total : 0.0;
        } finally {
            em.close();
        }
    }

    public long getDemandesCount() {
        return count("DemandeLocation");
    }

    public long getContratsCount() {
        return count("ContratLocation");
    }

    public long getPaiementsCount() {
        return count("Paiement");
    }

    public double getTotalPaiements() {
        return totalPaiements();
    }
}
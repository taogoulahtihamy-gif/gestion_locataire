package com.location.service;

import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Date;

public class DashboardService {

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

    public long countContratsActifs() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long total = em.createQuery(
                    "select count(c) from ContratLocation c where lower(c.statut) = 'actif'",
                    Long.class
            ).getSingleResult();
            return total != null ? total : 0L;
        } finally {
            em.close();
        }
    }

    public long countUnitesDisponibles() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long total = em.createQuery(
                    "select count(u) from UniteLocation u where lower(u.statut) = 'disponible'",
                    Long.class
            ).getSingleResult();
            return total != null ? total : 0L;
        } finally {
            em.close();
        }
    }

    public long countUnitesOccupees() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long total = em.createQuery(
                    "select count(u) from UniteLocation u where lower(u.statut) = 'occupée' or lower(u.statut) = 'occupee'",
                    Long.class
            ).getSingleResult();
            return total != null ? total : 0L;
        } finally {
            em.close();
        }
    }

    public long countPaiementsEnRetard() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long total = em.createQuery(
                            "select count(p) from Paiement p where p.dateEcheance < :today and p.statut = 'EN_RETARD'",
                            Long.class
                    ).setParameter("today", new Date())
                    .getSingleResult();
            return total != null ? total : 0L;
        } finally {
            em.close();
        }
    }

    public double tauxOccupation() {
        long totalUnites = getUnitesCount();
        if (totalUnites == 0) {
            return 0.0;
        }
        long occupees = countUnitesOccupees();
        return (occupees * 100.0) / totalUnites;
    }

    public long getImmeublesCount() {
        return count("Immeuble");
    }

    public long getUnitesCount() {
        return count("UniteLocation");
    }

    public long getLocatairesCount() {
        return count("Locataire");
    }

    public long getContratsCount() {
        return count("ContratLocation");
    }

    public long getPaiementsCount() {
        return count("Paiement");
    }

    public long getUtilisateursCount() {
        return count("Utilisateur");
    }

    public double getTotalPaiements() {
        return totalPaiements();
    }

    public long getContratsActifsCount() {
        return countContratsActifs();
    }

    public long getUnitesDisponiblesCount() {
        return countUnitesDisponibles();
    }

    public long getUnitesOccupeesCount() {
        return countUnitesOccupees();
    }

    public long getPaiementsEnRetardCount() {
        return countPaiementsEnRetard();
    }

    public double getTauxOccupation() {
        return tauxOccupation();
    }
}
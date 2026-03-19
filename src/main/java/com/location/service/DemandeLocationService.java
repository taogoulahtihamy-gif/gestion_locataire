package com.location.service;

import com.location.entity.DemandeLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DemandeLocationService {

    public List<DemandeLocation> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select d from DemandeLocation d " +
                            "left join fetch d.locataire " +
                            "left join fetch d.uniteLocation u " +
                            "left join fetch u.immeuble " +
                            "order by d.dateDemande desc, d.id desc",
                    DemandeLocation.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public DemandeLocation findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(DemandeLocation.class, id);
        } finally {
            em.close();
        }
    }

    public void updateStatut(Long id, String statut) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            DemandeLocation demande = em.find(DemandeLocation.class, id);
            if (demande != null) {
                demande.setStatut(statut);
                em.merge(demande);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public long countDemandes() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("select count(d) from DemandeLocation d", Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public long countDemandesEnAttente() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select count(d) from DemandeLocation d where upper(d.statut) = 'EN_ATTENTE'",
                    Long.class
            ).getSingleResult();
        } finally {
            em.close();
        }
    }
}
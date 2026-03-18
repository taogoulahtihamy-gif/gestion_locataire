package com.location.dao;

import com.location.entity.Paiement;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PaiementDao {

    public void save(Paiement entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Paiement findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(Paiement.class, id); } finally { em.close(); }
    }

    public List<Paiement> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM Paiement e ORDER BY e.id DESC", Paiement.class).getResultList(); }
        finally { em.close(); }
    }

    public Double sumMontants() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COALESCE(SUM(e.montant), 0) FROM Paiement e", Double.class).getSingleResult(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(e) FROM Paiement e", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Paiement entity = em.find(Paiement.class, id);
            if (entity != null) {
                em.getTransaction().begin();
                em.remove(entity);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }
}

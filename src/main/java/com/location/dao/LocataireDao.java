package com.location.dao;

import com.location.entity.Locataire;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LocataireDao {

    public void save(Locataire entity) {
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

    public Locataire findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(Locataire.class, id); } finally { em.close(); }
    }

    public List<Locataire> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM Locataire e ORDER BY e.id DESC", Locataire.class).getResultList(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(e) FROM Locataire e", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Locataire entity = em.find(Locataire.class, id);
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

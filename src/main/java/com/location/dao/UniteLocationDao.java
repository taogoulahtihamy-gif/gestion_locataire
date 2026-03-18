package com.location.dao;

import com.location.entity.UniteLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UniteLocationDao {

    public void save(UniteLocation entity) {
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

    public UniteLocation findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(UniteLocation.class, id); } finally { em.close(); }
    }

    public List<UniteLocation> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM UniteLocation e ORDER BY e.id DESC", UniteLocation.class).getResultList(); }
        finally { em.close(); }
    }

    public List<UniteLocation> findDisponibles() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM UniteLocation e WHERE e.statut = :statut ORDER BY e.id DESC", UniteLocation.class).setParameter("statut", "Disponible").getResultList(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(e) FROM UniteLocation e", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            UniteLocation entity = em.find(UniteLocation.class, id);
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

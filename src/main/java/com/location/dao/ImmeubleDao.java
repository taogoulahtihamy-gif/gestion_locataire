package com.location.dao;

import com.location.entity.Immeuble;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ImmeubleDao {

    public void save(Immeuble entity) {
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

    public Immeuble findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(Immeuble.class, id); } finally { em.close(); }
    }

    public List<Immeuble> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM Immeuble e ORDER BY e.id DESC", Immeuble.class).getResultList(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(e) FROM Immeuble e", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Immeuble entity = em.find(Immeuble.class, id);
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

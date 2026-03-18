package com.location.dao;

import com.location.entity.ContratLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContratLocationDao {

    public void save(ContratLocation entity) {
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

    public ContratLocation findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(ContratLocation.class, id); } finally { em.close(); }
    }

    public List<ContratLocation> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT e FROM ContratLocation e ORDER BY e.id DESC", ContratLocation.class).getResultList(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(e) FROM ContratLocation e", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            ContratLocation entity = em.find(ContratLocation.class, id);
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

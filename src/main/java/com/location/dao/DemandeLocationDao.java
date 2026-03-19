package com.location.dao;

import com.location.entity.DemandeLocation;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DemandeLocationDao {

    public void save(DemandeLocation entity) {
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

    public DemandeLocation findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(DemandeLocation.class, id);
        } finally {
            em.close();
        }
    }

    public List<DemandeLocation> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select d from DemandeLocation d order by d.id desc",
                    DemandeLocation.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            DemandeLocation entity = em.find(DemandeLocation.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
package com.location.dao;

import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class BaseDao<T> {
    private final Class<T> entityClass;

    protected BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (getId(entity) == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    public T findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(entityClass, id); } finally { em.close(); }
    }

    public List<T> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        } finally { em.close(); }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally { em.close(); }
    }

    protected abstract Long getId(T entity);
}

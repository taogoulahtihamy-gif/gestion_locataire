package com.location.dao;

import com.location.entity.Utilisateur;
import com.location.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UtilisateurDao {

    public void save(Utilisateur utilisateur) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            if (utilisateur.getId() == null) {
                em.persist(utilisateur);
            } else {
                em.merge(utilisateur);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Utilisateur findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.find(Utilisateur.class, id); } finally { em.close(); }
    }

    public Utilisateur findByEmail(String email) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Utilisateur u WHERE u.email = :email", Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Utilisateur> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT u FROM Utilisateur u ORDER BY u.id DESC", Utilisateur.class).getResultList(); }
        finally { em.close(); }
    }

    public long count() {
        EntityManager em = JpaUtil.getEntityManager();
        try { return em.createQuery("SELECT COUNT(u) FROM Utilisateur u", Long.class).getSingleResult(); }
        finally { em.close(); }
    }

    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Utilisateur entity = em.find(Utilisateur.class, id);
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

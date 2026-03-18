package com.location.util;

import com.location.entity.Immeuble;
import com.location.entity.UniteLocation;
import com.location.entity.Utilisateur;
import jakarta.persistence.EntityManager;

public class SeedData {

    public static void init() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            Long users = em.createQuery("select count(u) from Utilisateur u", Long.class).getSingleResult();

            if (users == 0) {
                em.getTransaction().begin();

                Utilisateur admin = new Utilisateur();
                admin.setNom("Administrateur");
                admin.setEmail("admin@demo.com");
                admin.setMotDePasse(PasswordUtil.hash("admin123"));
                admin.setRole("ADMIN");
                admin.setActif(true);
                em.persist(admin);

                Utilisateur owner = new Utilisateur();
                owner.setNom("Propriétaire Demo");
                owner.setEmail("owner@demo.com");
                owner.setMotDePasse(PasswordUtil.hash("owner123"));
                owner.setRole("PROPRIETAIRE");
                owner.setActif(true);
                em.persist(owner);

                Immeuble imm = new Immeuble();
                imm.setNom("Résidence Horizon");
                imm.setAdresse("Dakar, Liberté 6");
                imm.setNombreUnites(2);
                imm.setEquipements("Parking, gardiennage, Wi-Fi");
                imm.setDescription("Immeuble témoin pour démarrer l'application.");
                em.persist(imm);

                UniteLocation u1 = new UniteLocation();
                u1.setImmeuble(imm);
                u1.setReference("A101");
                u1.setNombrePieces(2);
                u1.setSuperficie(75.0);
                u1.setLoyerMensuel(150000.0);
                u1.setStatut("Disponible");
                u1.setEquipements("Climatisation, balcon");
                em.persist(u1);

                UniteLocation u2 = new UniteLocation();
                u2.setImmeuble(imm);
                u2.setReference("B201");
                u2.setNombrePieces(3);
                u2.setSuperficie(98.0);
                u2.setLoyerMensuel(250000.0);
                u2.setStatut("Disponible");
                u2.setEquipements("Cuisine équipée, chauffe-eau");
                em.persist(u2);

                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
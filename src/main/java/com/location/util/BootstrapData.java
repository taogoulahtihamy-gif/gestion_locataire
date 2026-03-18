package com.location.util;

import com.location.dao.UtilisateurDao;
import com.location.entity.Utilisateur;

public class BootstrapData {

    private BootstrapData() {
    }

    public static void initialize() {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        if (utilisateurDao.count() == 0) {
            Utilisateur admin = new Utilisateur();
            admin.setNom("Administrateur");
            admin.setEmail("admin@admin.com");
            admin.setMotDePasse(PasswordUtil.hash("admin123"));
            admin.setRole("ADMIN");
            utilisateurDao.save(admin);
        }
    }
}

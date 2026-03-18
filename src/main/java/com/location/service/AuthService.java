package com.location.service;

import com.location.dao.UtilisateurDao;
import com.location.entity.Utilisateur;
import com.location.util.PasswordUtil;

public class AuthService {

    private final UtilisateurDao utilisateurDao = new UtilisateurDao();

    public Utilisateur login(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDao.findByEmail(email);
        if (utilisateur == null) return null;
        return PasswordUtil.hash(motDePasse).equals(utilisateur.getMotDePasse()) ? utilisateur : null;
    }
}

package com.location.service;

import com.location.dao.UtilisateurDao;
import com.location.entity.Utilisateur;

import java.util.List;

public class UtilisateurService {

    private final UtilisateurDao dao = new UtilisateurDao();

    public void save(Utilisateur entity) { dao.save(entity); }
    public Utilisateur findById(Integer id) { return dao.findById(id); }
    public List<Utilisateur> findAll() { return dao.findAll(); }
    public void delete(Integer id) { dao.delete(id); }
    public long count() { return dao.count(); }
}

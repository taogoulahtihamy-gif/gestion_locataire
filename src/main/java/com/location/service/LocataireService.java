package com.location.service;

import com.location.dao.LocataireDao;
import com.location.entity.Locataire;

import java.util.List;

public class LocataireService {

    private final LocataireDao dao = new LocataireDao();

    public void save(Locataire entity) { dao.save(entity); }
    public Locataire findById(Integer id) { return dao.findById(id); }
    public List<Locataire> findAll() { return dao.findAll(); }
    public void delete(Integer id) { dao.delete(id); }
    public long count() { return dao.count(); }
}

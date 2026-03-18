package com.location.service;

import com.location.dao.DemandeLocationDao;
import com.location.entity.DemandeLocation;
import java.util.List;

public class DemandeService {
    private final DemandeLocationDao dao = new DemandeLocationDao();
    public void save(DemandeLocation entity) { dao.save(entity); }
    public DemandeLocation findById(Long id) { return dao.findById(id); }
    public List<DemandeLocation> findAll() { return dao.findAll(); }
    public void delete(Long id) { dao.delete(id); }
}

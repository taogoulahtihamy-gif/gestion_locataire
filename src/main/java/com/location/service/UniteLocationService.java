package com.location.service;

import com.location.dao.UniteLocationDao;
import com.location.entity.UniteLocation;

import java.util.List;

public class UniteLocationService {

    private final UniteLocationDao dao = new UniteLocationDao();

    public void save(UniteLocation entity) { dao.save(entity); }
    public UniteLocation findById(Integer id) { return dao.findById(id); }
    public List<UniteLocation> findAll() { return dao.findAll(); }
    public void delete(Integer id) { dao.delete(id); }
    public long count() { return dao.count(); }
    public java.util.List<com.location.entity.UniteLocation> getDisponibles() { return dao.findDisponibles(); }

}

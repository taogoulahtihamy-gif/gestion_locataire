package com.location.service;

import com.location.dao.ContratLocationDao;
import com.location.entity.ContratLocation;

import java.util.List;

public class ContratService {

    private final ContratLocationDao dao = new ContratLocationDao();

    public void save(ContratLocation entity) { dao.save(entity); }
    public ContratLocation findById(Integer id) { return dao.findById(id); }
    public List<ContratLocation> findAll() { return dao.findAll(); }
    public void delete(Integer id) { dao.delete(id); }
    public long count() { return dao.count(); }
}

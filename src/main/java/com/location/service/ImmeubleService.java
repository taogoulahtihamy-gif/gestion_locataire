package com.location.service;

import com.location.dao.ImmeubleDao;
import com.location.entity.Immeuble;

import java.util.List;

public class ImmeubleService {

    private final ImmeubleDao dao = new ImmeubleDao();

    public void save(Immeuble entity) { dao.save(entity); }
    public Immeuble findById(Integer id) { return dao.findById(id); }
    public List<Immeuble> findAll() { return dao.findAll(); }
    public void delete(Integer id) { dao.delete(id); }
    public long count() { return dao.count(); }
}

package com.location.service;

import com.location.dao.UniteLocationDao;
import com.location.entity.UniteLocation;
import java.util.List;

public class UniteService {
    private final UniteLocationDao dao = new UniteLocationDao();
    public void save(UniteLocation entity) { dao.save(entity); }
    public UniteLocation findById(Long id) {
        return dao.findById(id.intValue());
    }

    public void delete(Long id) {
        dao.delete(id.intValue());
    }
    public List<UniteLocation> disponibles() { return dao.findDisponibles(); }

}

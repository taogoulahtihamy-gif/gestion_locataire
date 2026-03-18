package com.location.dao;

import com.location.entity.DemandeLocation;

public class DemandeLocationDao extends BaseDao<DemandeLocation> {
    public DemandeLocationDao() { super(DemandeLocation.class); }
    @Override protected Long getId(DemandeLocation entity) { return entity.getId(); }
}

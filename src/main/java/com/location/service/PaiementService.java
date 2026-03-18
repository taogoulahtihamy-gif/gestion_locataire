package com.location.service;

import com.location.dao.PaiementDao;
import com.location.entity.Paiement;

import java.util.List;

public class PaiementService {

    private final PaiementDao dao = new PaiementDao();

    public void save(Paiement entity) {
        dao.save(entity);
    }

    public Paiement findById(Integer id) {
        return dao.findById(id);
    }

    public List<Paiement> findAll() {
        return dao.findAll();
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public long count() {
        return dao.count();
    }

    public Double getTotalMontants() {
        return dao.sumMontants();
    }

    public double getTotalPaiements() {
        Double total = dao.sumMontants();
        return total != null ? total : 0.0;
    }
}
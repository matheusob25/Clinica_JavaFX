package com.example.clinica.model.services;

import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.dao.NeighborhoodDao;
import com.example.clinica.model.entities.Neighborhood;

public class NeighborHoodService {
    private NeighborhoodDao neighborhoodDao;

    public NeighborHoodService() {
        this.neighborhoodDao = DaoFactory.createNeighborhoodDao();
    }
    public void insert(Neighborhood neighborhood) {
        neighborhoodDao.insert(neighborhood);
    }
    public void update(Neighborhood neighborhood) {
        neighborhoodDao.update(neighborhood);
    }
    public void insertOrUpdate(Neighborhood neighborhood) {
        if (neighborhood.getId() == null) {
            insert(neighborhood);
        }else{
            update(neighborhood);
        }
    }
    public Neighborhood findByName(String name) {
        return neighborhoodDao.findByName(name);
    }
}

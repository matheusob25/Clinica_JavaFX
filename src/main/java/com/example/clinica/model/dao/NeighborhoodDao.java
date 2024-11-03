package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Address;
import com.example.clinica.model.entities.Neighborhood;

public interface NeighborhoodDao {
    void insert(Neighborhood neighborhood);
    void update(Neighborhood neighborhood);
    void delete(Neighborhood neighborhood);
    Neighborhood findByAddress(Address address);
}


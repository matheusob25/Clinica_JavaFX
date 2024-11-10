package com.example.clinica.model.dao;

import com.example.clinica.model.entities.City;

public interface CityDao {

    void insert(City city);
    void update(City city);
    void delete(City city);
    City findByName(String name);


}

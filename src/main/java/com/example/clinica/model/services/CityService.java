package com.example.clinica.model.services;

import com.example.clinica.model.dao.CityDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.City;

public class CityService {
    private CityDao cityDao;

    public CityService() {
        cityDao = DaoFactory.createCityDao();
    }

    public void insert(City city) {
        cityDao.insert(city);
    }
    public void update(City city) {
        cityDao.update(city);
    }
    public void insertOrUpdate(City city) {
        if (city.getId() == null) {
            insert(city);
        }else{
            update(city);
        }
    }
    public City findByName(String name){
        return cityDao.findByName(name);
    }
}

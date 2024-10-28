package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Pacient;

import java.util.List;

public interface PacientDao {

    void insert(Pacient pacient);
    void update(Pacient pacient);
    void deleteById(Integer id);
    Pacient findById(Integer id);
    List<Pacient> findAll();

}

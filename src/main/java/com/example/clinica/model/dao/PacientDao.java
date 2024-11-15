package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Pacient;

import java.util.List;

public interface PacientDao {

    void insert(Pacient pacient);
    void update(Pacient pacient);
    void deleteById(Long id);
    Pacient findById(Long id);
    List<Pacient> findAll();
    Long count();


}


package com.example.clinica.model.dao.impl;

import com.example.clinica.model.dao.AnamneseDao;
import com.example.clinica.model.entities.Anamnese;
import com.example.clinica.model.entities.Pacient;

import java.sql.Connection;

public class AnamneseDaoJDBC implements AnamneseDao {
    private Connection connection;

    public AnamneseDaoJDBC(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Anamnese anamnese) {

    }

    @Override
    public void update(Anamnese anamnese) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Anamnese findByPacient(Pacient pacient) {
        return null;
    }
}

package com.example.clinica.model.services;

import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.dao.PacientDao;
import com.example.clinica.model.entities.Pacient;

import java.util.List;

public class PacientService {
    private final PacientDao pacientDao;

    public PacientService() {
       this.pacientDao = DaoFactory.createPacienteDao();

    }
    public void insert(Pacient pacient) {
        pacientDao.insert(pacient);
    }
    public List<Pacient> findAll() {
        return pacientDao.findAll();
    }



}

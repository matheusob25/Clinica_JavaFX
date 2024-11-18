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
    private void insert(Pacient pacient) {
        pacientDao.insert(pacient);
    }
    public List<Pacient> findAll() {
        return pacientDao.findAll();
    }
    public void saveOrUpdate(Pacient pacient) {
        if (pacient.getId() == null) {
            insert(pacient);
        }else{
            update(pacient);
        }
    }
    public void update(Pacient pacient) {
        pacientDao.update(pacient);
    }
    public Pacient findById(Long id) {
        return pacientDao.findById(id);
    }

    public void delete(Long id) {
        pacientDao.deleteById(id);
    }
    public Long count() {
        return pacientDao.count();
    }
    public Long countActivePacients(){
        return pacientDao.countActivePacients();
    }


}

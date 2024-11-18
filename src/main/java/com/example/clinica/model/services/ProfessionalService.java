package com.example.clinica.model.services;

import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.dao.ProfessionalDao;
import com.example.clinica.model.entities.Professional;

import java.util.List;

public class ProfessionalService {
    private ProfessionalDao professionalDao;
    public ProfessionalService() {
        this.professionalDao = DaoFactory.createProfessionalDao();
    }
    public void insert(Professional professional) {
        professionalDao.insert(professional);
    }
    public void update(Professional professional) {
        professionalDao.update(professional);
    }
    public void delete(Long id) {
        if(id == null ){
            throw new IllegalArgumentException("id can not be null");
        }
        professionalDao.deletebyId(id);
    }
    public List<Professional> findAll() {
        return professionalDao.findAll();
    }
    public Professional findById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id can not be null");
        }
         return  professionalDao.findbyId(id);
    }
    public Long count() {
        return professionalDao.count();
    }


}

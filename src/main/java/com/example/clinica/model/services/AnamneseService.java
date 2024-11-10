package com.example.clinica.model.services;

import com.example.clinica.model.dao.AnamneseDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.Anamnese;

public class AnamneseService {
    private AnamneseDao anamneseDao;
    public AnamneseService() {
        this.anamneseDao = DaoFactory.createAnamneseDao();
    }
    public void insert(Anamnese anamnese) {
        anamneseDao.insert(anamnese);
    }
    public void update(Anamnese anamnese) {
        anamneseDao.update(anamnese);
    }
    public void insertOrUpdate(Anamnese anamnese) {
        if (anamnese.getId() == null) {
            insert(anamnese);
        }else{
            update(anamnese);
        }
    }
}

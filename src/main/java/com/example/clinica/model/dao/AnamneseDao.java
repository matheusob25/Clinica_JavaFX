package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Anamnese;
import com.example.clinica.model.entities.Pacient;

public interface AnamneseDao {

    void insert(Anamnese anamnese);
    void update(Anamnese anamnese);
    void deleteById(Long id);
    Anamnese findByPacient(Pacient pacient);

}

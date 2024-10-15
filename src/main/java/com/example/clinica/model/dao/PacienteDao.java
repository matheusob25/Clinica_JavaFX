package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Paciente;

import java.util.List;

public interface PacienteDao {

    void insert(Paciente paciente);
    void update(Paciente paciente);
    void deleteById(Integer id);
    Paciente findById(Integer id);
    List<Paciente> findAll();

}

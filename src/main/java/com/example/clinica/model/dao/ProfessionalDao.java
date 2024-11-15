package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Professional;

import java.util.List;

public interface ProfessionalDao {
    void insert(Professional professional);
    void update(Professional professional);
    void deletebyId(Long id);
    Professional findbyId(Long id);
    List<Professional> findAll();
    Long count();
}

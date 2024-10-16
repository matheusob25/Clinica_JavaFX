package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> findAll();
    Admin findById(Long id);
    void insert(Admin admin);
    void deleteById(Long id);
    void update(Admin admin);
    Admin findByName(String name);
}

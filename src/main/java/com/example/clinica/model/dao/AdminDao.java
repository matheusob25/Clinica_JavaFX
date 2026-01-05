package com.example.clinica.model.dao;

import com.example.clinica.model.entities.User;


import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    void insert(User admin);
    void deleteById(Long id);
    void update(User admin);
    User findByName(String name);
}

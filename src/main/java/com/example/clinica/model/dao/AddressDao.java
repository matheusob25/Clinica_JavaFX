package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Address;
import com.example.clinica.model.entities.Pacient;

import java.sql.Connection;
import java.util.List;

public interface AddressDao{
    void insert(Address address);
    void update(Address address);
    void delete(Address address);
    Address getByPacient(Pacient pacient);
}

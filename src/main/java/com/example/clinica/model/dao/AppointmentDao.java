package com.example.clinica.model.dao;

import com.example.clinica.model.entities.Appointment;
import com.example.clinica.model.entities.Pacient;
import com.example.clinica.model.entities.Professional;

import java.util.List;

public interface AppointmentDao {
    void insert(Appointment appointment);
    void update(Appointment appointment);
    void delete(Long id);
    Appointment findById(Long id);
    List<Appointment> findAll();
    List<Appointment> findByPatient(Long pacientId);
    List<Appointment> findByProfessional(Long professionalId);
    Long count();
}

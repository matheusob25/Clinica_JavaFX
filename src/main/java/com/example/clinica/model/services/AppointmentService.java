package com.example.clinica.model.services;

import com.example.clinica.model.dao.AppointmentDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.Appointment;

import java.util.List;

public class AppointmentService {
    private AppointmentDao appointmentDao;
    public AppointmentService( ) {
        this.appointmentDao = DaoFactory.createAppointmentDao();
    }
    public void insert(Appointment appointment) {
        appointmentDao.insert(appointment);
    }
    public void update(Appointment appointment) {
        appointmentDao.update(appointment);
    }
    public void delete(Long id) {
        appointmentDao.delete(id);
    }
    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }
    public Appointment findById(Long id) {
        return appointmentDao.findById(id);
    }
    public List<Appointment> findByPatient(Long id) {
        return appointmentDao.findByPatient(id);
    }
    public List<Appointment> findByProfessional(Long id) {
        return appointmentDao.findByProfessional(id);
    }
     public Long count(){
        return appointmentDao.count();
     }
}

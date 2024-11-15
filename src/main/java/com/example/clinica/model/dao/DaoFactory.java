package com.example.clinica.model.dao;

import com.example.clinica.db.DB;
import com.example.clinica.model.dao.impl.*;
import com.example.clinica.model.entities.Anamnese;

public class DaoFactory {
    public static PacientDao createPacienteDao() {
        return new PacientDaoJDBC(DB.getConnection());
    }
    public static AdminDao createAdminDao() {
        return new AdminDaoJDBC(DB.getConnection());
    }
    public static AnamneseDao createAnamneseDao() {
        return new AnamneseDaoJDBC(DB.getConnection());
    }
    public static AddressDao createAddressDao() {
        return new AddressDaoJDBC(DB.getConnection());

    }
    public static NeighborhoodDao createNeighborhoodDao() {
        return new NeighborhoodDaoJDBC(DB.getConnection());
    }
    public static CityDao createCityDao() {
        return new CityDaoJDBC(DB.getConnection());
    }
    public static AppointmentDao createAppointmentDao() {
        return new AppointmentDaoJDBC(DB.getConnection());
    }



}

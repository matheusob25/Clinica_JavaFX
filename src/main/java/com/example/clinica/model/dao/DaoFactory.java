package com.example.clinica.model.dao;

import com.example.clinica.db.DB;
import com.example.clinica.model.dao.impl.AddressDaoJDBC;
import com.example.clinica.model.dao.impl.AdminDaoJDBC;
import com.example.clinica.model.dao.impl.AnamneseDaoJDBC;
import com.example.clinica.model.dao.impl.PacientDaoJDBC;
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




}

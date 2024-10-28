package com.example.clinica.model.dao;

import com.example.clinica.db.DB;
import com.example.clinica.model.dao.impl.AdminDaoJDBC;
import com.example.clinica.model.dao.impl.PacientDaoJDBC;

public class DaoFactory {
    public static PacientDao createPacienteDao() {
        return new PacientDaoJDBC(DB.getConnection());
    }
    public static AdminDao createAdminDao() {
        return new AdminDaoJDBC(DB.getConnection());
    }




}

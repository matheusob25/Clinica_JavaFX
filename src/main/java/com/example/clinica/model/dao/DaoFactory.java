package com.example.clinica.model.dao;

import com.example.clinica.db.DB;
import com.example.clinica.model.dao.impl.PacienteDaoJDBC;

public class DaoFactory {
    public static PacienteDao createPacienteDao() {
        return new PacienteDaoJDBC(DB.getConnection());
    }




}

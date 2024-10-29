package com.example.clinica.model.services;

import com.example.clinica.model.dao.AdminDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.utils.PasswordEncryptor;

public class AuthenticateService {
    private final AdminDao adminDao;

    public AuthenticateService() {
        this.adminDao = DaoFactory.createAdminDao();
    }
    public boolean login(String name, String password) {

            Admin admin = adminDao.findByName(name);
            return admin != null && PasswordEncryptor.checkPassword(password.strip(), admin.getPassword());

    }
}

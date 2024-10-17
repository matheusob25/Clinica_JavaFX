package com.example.clinica.model.services;

import com.example.clinica.model.dao.AdminDao;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.utils.PasswordEncryptor;

public class AuthenticateService {
    private final AdminDao adminDao;

    public AuthenticateService(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    public boolean login(String name, String password) {

            Admin admin = adminDao.findByName(name);
            return admin != null && PasswordEncryptor.checkPassword(password, admin.getPassword());

    }
}

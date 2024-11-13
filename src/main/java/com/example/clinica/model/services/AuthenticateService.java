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
        Admin admin = findByName(name);
        return admin != null && PasswordEncryptor.checkPassword(password, admin.getPassword());
    }
    public Admin findByName(String name) {
         return adminDao.findByName(name);
    }
    public void update(Admin admin) {
        admin.setPassword(PasswordEncryptor.hash(admin.getPassword()));
        adminDao.update(admin);
    }
}

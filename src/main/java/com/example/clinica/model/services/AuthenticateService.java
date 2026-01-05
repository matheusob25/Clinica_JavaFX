package com.example.clinica.model.services;

import com.example.clinica.model.dao.AdminDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.User;
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
    public User findByName(String name) {
         return adminDao.findByName(name);
    }
    public void update(User user) {
        user.setPassword(PasswordEncryptor.hash(user.getPassword()));
        adminDao.update(user);
    }
}

package com.example.clinica.model.services;

import com.example.clinica.model.dao.AdminDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.dao.impl.AdminDaoJDBC;
import com.example.clinica.model.entities.Admin;
import com.example.clinica.utils.PasswordEncryptor;

public class AdminService {
    private final AdminDao adminDao;

    public AdminService(AdminDao adminDao) {
        this.adminDao =  DaoFactory.createAdminDao();
    }
    public boolean login(String name, String password) {
        Admin admin = adminDao.findByName(name);
        if(admin != null && PasswordEncryptor.checkPassword(password, admin.getPassword())) {
            return true;
        }else{
            return false;
        }
    }
}

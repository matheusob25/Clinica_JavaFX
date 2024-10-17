package com.example.clinica.model.services;

import com.example.clinica.model.dao.impl.AdminDaoJDBC;

public class AdminSession {
    private static AdminSession instance;
    private Long id;
    private String name;
    private AdminSession(){

    }

    public static AdminSession getInstance(){
        if(instance == null){
            instance = new AdminSession();
        }
        return instance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

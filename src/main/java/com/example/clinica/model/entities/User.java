package com.example.clinica.model.entities;

import com.example.clinica.model.entities.enums.Role;

public class User {
    private Long id;
    private String name;
    private String password;
    private Role role;

    public User() {

    }
    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

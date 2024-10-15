package com.example.clinica.db;


public class DbIntegrityException extends RuntimeException{

    public DbIntegrityException(String message) {
        super(message);
    }
}
package com.example.clinica.controllers;

import com.example.clinica.model.services.PacientService;

public class PacientViewController {
    private PacientService pacientService;

    public PacientViewController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

}

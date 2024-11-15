package com.example.clinica.model.entities;

import com.example.clinica.model.entities.enums.Status;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {

    private Long id;
    private String description;
    private LocalDateTime dateTime;
    private LocalTime duration;
    private Integer status;
    private Pacient pacient;
    private Professional professional;
    public Appointment() {

    }

    public Appointment(Long id, String description, LocalDateTime dateTime, LocalTime duration, Status status, Pacient pacient, Professional professional) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
        this.duration = duration;
        setStatus(status);
        this.pacient = pacient;
        this.professional = professional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public Status getStatus() {
        return Status.valueOf(status);
    }
    public void setStatus(Status status) {
        if(status != null){
            this.status = status.getValue();
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

}

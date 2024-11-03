package com.example.clinica.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Pacient implements Serializable {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String number;
    private LocalDate birthDate;
    private String cpf;
    private LocalDate startTreatment;
    private LocalDate endTreatment;
    private String profession;
    private String maritalStatus;
    private String dlne;
    private Address address;
    private Anamnese anamnese;

    public Pacient(){

    }

    public Pacient(Long id, String name, String email, String number, LocalDate birthDate, String cpf, LocalDate startTreatment, LocalDate endTreatment, String profession, String maritalStatus, String dlne, Address address, Anamnese anamnese) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.startTreatment = startTreatment;
        this.endTreatment = endTreatment;
        this.profession = profession;
        this.maritalStatus = maritalStatus;
        this.dlne = dlne;
        this.address = address;
        this.anamnese = anamnese;
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
    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getStartTreatment() {
        return startTreatment;
    }

    public void setStartTreatment(LocalDate startTreatment) {
        this.startTreatment = startTreatment;
    }

    public LocalDate getEndTreatment() {
        return endTreatment;
    }

    public void setEndTreatment(LocalDate endTreatment) {
        this.endTreatment = endTreatment;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDlne() {
        return dlne;
    }

    public void setDlne(String dlne) {
        this.dlne = dlne;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacient pacient = (Pacient) o;
        return Objects.equals(id, pacient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

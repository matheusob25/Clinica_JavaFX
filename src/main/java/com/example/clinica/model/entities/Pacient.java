package com.example.clinica.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Pacient implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String number;
    private LocalDate birthDate;
    private String cpf;
    private Address address;
    private Anamnese anamnese;

    public Pacient(){

    }
    public Pacient(String name, String email, String number, LocalDate birthDate, String cpf, Address address) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.address = address;
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

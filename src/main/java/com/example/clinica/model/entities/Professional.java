package com.example.clinica.model.entities;


public class Professional {
    private Long id;
    private String name;
    private String number;
    private String descricao;

    public Professional(String name, String number, String descricao) {
        this.name = name;
        this.number = number;
        this.descricao = descricao;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Professional => " +
                "id: " + id +
                ", nome: " + name +
                ", number: " + number +
                ", descricao: " + descricao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

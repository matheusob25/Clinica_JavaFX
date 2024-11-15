package com.example.clinica.model.entities;


public class Professional {
    private Long id;
    private String name;
    private String number;
    private String descricao;

    public Professional() {
    }

    public Professional(Long id, String name, String number, String descricao) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

package com.example.clinica.model.entities;


public class Profissional {
    private Long id;
    private String name;
    private String numero;
    private String descricao;

    public Profissional(String name, String numero, String descricao) {
        this.name = name;
        this.numero = numero;
        this.descricao = descricao;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Profissional => " +
                "id: " + id +
                ", nome: " + name +
                ", numero: " + numero +
                ", descricao: " + descricao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

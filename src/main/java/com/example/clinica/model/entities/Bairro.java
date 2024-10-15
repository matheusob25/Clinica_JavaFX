package com.example.clinica.model.entities;

public class Bairro {
    private Integer id;
    private String nome;
    private City city;

    public Bairro() {
    }

    public Bairro(Integer id, String nome, City city) {
        this.id = id;
        this.nome = nome;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Bairro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", city=" + city +
                '}';
    }
}

package com.example.clinica.model.entities;

public class Neighborhood {
    private Long id;
    private String nome;
    private City city;

    public Neighborhood() {
    }

    public Neighborhood(Long id, String nome, City city) {
        this.id = id;
        this.nome = nome;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Neighborhood{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", city=" + city +
                '}';
    }
}

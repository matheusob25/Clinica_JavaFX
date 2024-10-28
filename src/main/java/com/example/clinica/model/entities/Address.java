package com.example.clinica.model.entities;

public class Address {
    private Long id;
    private String descricao;
    private Neighborhood neighborhood;
    private String reference;
    public Address() {

    }

    public Address(Long id, String descricao, Neighborhood neighborhood, String reference) {
        this.id = id;
        this.descricao = descricao;
        this.neighborhood = neighborhood;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Neighborhood getBairro() {
        return neighborhood;
    }

    public void setBairro(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", neighborhood=" + neighborhood +
                ", reference='" + reference + '\'' +
                '}';
    }
}

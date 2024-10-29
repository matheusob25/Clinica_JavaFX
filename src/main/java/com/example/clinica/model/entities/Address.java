package com.example.clinica.model.entities;

public class Address {
    private Long id;
    private String description;
    private Neighborhood neighborhood;
    private String reference;
    public Address() {

    }

    public Address(Long id, String description, Neighborhood neighborhood, String reference) {
        this.id = id;
        this.description = description;
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
        return description;
    }

    public void setDescricao(String description) {
        this.description = description;
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
                ", description='" + description + '\'' +
                ", neighborhood=" + neighborhood +
                ", reference='" + reference + '\'' +
                '}';
    }
}

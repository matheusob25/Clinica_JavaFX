package com.example.clinica.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Address {
    private Long id;
    private String descricao;
    private Bairro bairro;
    private String referencia;
    public Address() {

    }

    public Address(Long id, String descricao, Bairro bairro, String referencia) {
        this.id = id;
        this.descricao = descricao;
        this.bairro = bairro;
        this.referencia = referencia;
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

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", bairro=" + bairro +
                ", referencia='" + referencia + '\'' +
                '}';
    }
}

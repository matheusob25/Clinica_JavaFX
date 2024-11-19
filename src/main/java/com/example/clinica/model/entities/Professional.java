package com.example.clinica.model.entities;


public class Professional {
    private Long id;
    private String name;
    private String number;
    private String description;

    public Professional() {
    }

    public Professional(Long id, String name, String number, String description) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Professional => " +
                "id: " + id +
                ", name: " + name +
                ", number: " + number +
                ", description: " + description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.clinica.model.entities;

import java.util.Objects;

public class Anamnese {
    private Long id;
    private String toothColor;
    private String toothEsc;
    private String toothShape;
    private String sensitivityAnesthesia;
    private String sensitivityAntibiotics;
    private String medicationUse;
    private String additionalAnnotations;

    public Anamnese() {
    }
    public Anamnese (Long id, String toothColor, String toothEsc, String toothShape, String sensitivityAnesthesia, String sensitivityAntibiotics, String medicationUse, String additionalAnnotations) {
        this.id = id;
        this.toothColor = toothColor;
        this.toothEsc = toothEsc;
        this.toothShape = toothShape;
        this.sensitivityAnesthesia = sensitivityAnesthesia;
        this.sensitivityAntibiotics = sensitivityAntibiotics;
        this.medicationUse = medicationUse;
        this.additionalAnnotations = additionalAnnotations;
    }


    public String getToothColor() {
        return toothColor;
    }

    public void setToothColor(String toothColor) {
        this.toothColor = toothColor;
    }

    public String getToothEsc() {
        return toothEsc;
    }

    public void setToothEsc(String toothEsc) {
        this.toothEsc = toothEsc;
    }

    public String getToothShape() {
        return toothShape;
    }

    public void setToothShape(String toothShape) {
        this.toothShape = toothShape;
    }

    public String getSensitivityAnesthesia() {
        return sensitivityAnesthesia;
    }

    public void setSensitivityAnesthesia(String sensitivityAnesthesia) {
        this.sensitivityAnesthesia = sensitivityAnesthesia;
    }

    public String getSensitivityAntibiotics() {
        return sensitivityAntibiotics;
    }

    public void setSensitivityAntibiotics(String sensitivityAntibiotics) {
        this.sensitivityAntibiotics = sensitivityAntibiotics;
    }
    public String getMedicationUse() {
        return medicationUse;
    }

    public void setMedicationUse(String medicationUse) {
        this.medicationUse = medicationUse;
    }

    public String getAdditionalAnnotations() {
        return additionalAnnotations;
    }
    public void setAdditionalAnnotations(String additionalAnnotations) {
        this.additionalAnnotations = additionalAnnotations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anamnese anamnese = (Anamnese) o;
        return Objects.equals(id, anamnese.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}

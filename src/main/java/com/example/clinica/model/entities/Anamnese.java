package com.example.clinica.model.entities;

import java.util.Objects;

public class Anamnese {
    private Long id;
    private String sensitivityAnesthesia;
    private String sensitivityAntibiotics;
    private String medicationUse;
    private String seriousIllness;
    private Boolean sensitiveTooth;
    private Boolean pregnancy;
    private Boolean diabetes;
    private String additionalAnnotations;

    public Anamnese() {
    }

    public Anamnese(Long id, String sensitivityAnesthesia, String sensitivityAntibiotics, String medicationUse, String seriousIllness, Boolean sensitiveTooth, Boolean pregnancy, Boolean diabetes, String additionalAnnotations) {
        this.id = id;
        this.sensitivityAnesthesia = sensitivityAnesthesia;
        this.sensitivityAntibiotics = sensitivityAntibiotics;
        this.medicationUse = medicationUse;
        this.seriousIllness = seriousIllness;
        this.sensitiveTooth = sensitiveTooth;
        this.pregnancy = pregnancy;
        this.diabetes = diabetes;
        this.additionalAnnotations = additionalAnnotations;
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

    public String getSeriousIllness() {
        return seriousIllness;
    }

    public void setSeriousIllness(String seriousIllness) {
        this.seriousIllness = seriousIllness;
    }

    public Boolean getSensitiveTooth() {
        return sensitiveTooth;
    }

    public void setSensitiveTooth(Boolean sensitiveTooth) {
        this.sensitiveTooth = sensitiveTooth;
    }

    public Boolean getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(Boolean pregnancy) {
        this.pregnancy = pregnancy;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
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

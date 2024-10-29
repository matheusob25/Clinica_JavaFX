package com.example.clinica.model.entities;

import java.util.Objects;

public class Anamnese {
    private Long id;
    private String sensitivityAnesthesia;
    private String sensitivityAntibiotics;
    private String tc;
    private String ts;
    private String paMax;
    private String paMin;
    private String medicationUse;
    private String cancerHistory;
    private String seriousIllness;
    private String oralHygiene;
    private Boolean sensitiveTooth;
    private String pregnancy;
    private String diabetes;

    public Anamnese() {
    }

    public Anamnese(Long id, String sensitivityAnesthesia, String sensitivityAntibiotics, String tc, String ts, String paMax, String paMin, String medicationUse, String cancerHistory, String seriousIllness, String oralHygiene, Boolean sensitiveTooth, String pregnancy, String diabetes) {
        this.id = id;
        this.sensitivityAnesthesia = sensitivityAnesthesia;
        this.sensitivityAntibiotics = sensitivityAntibiotics;
        this.tc = tc;
        this.ts = ts;
        this.paMax = paMax;
        this.paMin = paMin;
        this.medicationUse = medicationUse;
        this.cancerHistory = cancerHistory;
        this.seriousIllness = seriousIllness;
        this.oralHygiene = oralHygiene;
        this.sensitiveTooth = sensitiveTooth;
        this.pregnancy = pregnancy;
        this.diabetes = diabetes;
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

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getPaMax() {
        return paMax;
    }

    public void setPaMax(String paMax) {
        this.paMax = paMax;
    }

    public String getPaMin() {
        return paMin;
    }

    public void setPaMin(String paMin) {
        this.paMin = paMin;
    }

    public String getMedicationUse() {
        return medicationUse;
    }

    public void setMedicationUse(String medicationUse) {
        this.medicationUse = medicationUse;
    }

    public String getCancerHistory() {
        return cancerHistory;
    }

    public void setCancerHistory(String cancerHistory) {
        this.cancerHistory = cancerHistory;
    }

    public String getSeriousIllness() {
        return seriousIllness;
    }

    public void setSeriousIllness(String seriousIllness) {
        this.seriousIllness = seriousIllness;
    }

    public String getOralHygiene() {
        return oralHygiene;
    }

    public void setOralHygiene(String oralHygiene) {
        this.oralHygiene = oralHygiene;
    }

    public Boolean getSensitiveTooth() {
        return sensitiveTooth;
    }

    public void setSensitiveTooth(Boolean sensitiveTooth) {
        this.sensitiveTooth = sensitiveTooth;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
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

    @Override
    public String toString() {
        return "Anamnese{" +
                "id=" + id +
                ", sensitivityAnesthesia='" + sensitivityAnesthesia + '\'' +
                ", sensitivityAntibiotics='" + sensitivityAntibiotics + '\'' +
                ", tc='" + tc + '\'' +
                ", ts='" + ts + '\'' +
                ", paMax='" + paMax + '\'' +
                ", paMin='" + paMin + '\'' +
                ", medicationUse='" + medicationUse + '\'' +
                ", cancerHistory='" + cancerHistory + '\'' +
                ", seriousIllness='" + seriousIllness + '\'' +
                ", oralHygiene='" + oralHygiene + '\'' +
                ", sensitiveTooth=" + sensitiveTooth +
                ", pregnancy='" + pregnancy + '\'' +
                ", diabetes='" + diabetes + '\'' +
                '}';
    }
}

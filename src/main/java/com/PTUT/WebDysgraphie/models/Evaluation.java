package com.PTUT.WebDysgraphie.models;

public class Evaluation {
    private Patient patient;
    private typeTest typetest;
    private typeMateriel typemateriel;

    public Evaluation(Patient patient, typeTest typetest, typeMateriel typemateriel) {
        this.patient = patient;
        this.typetest= typetest;
        this.typemateriel = typemateriel;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public typeTest getTypetest() {
        return typetest;
    }

    public void setTypetest(typeTest typetest) {
        this.typetest = typetest;
    }

    public typeMateriel getTypemateriel() {
        return typemateriel;
    }

    public void setTypemateriel(typeMateriel typemateriel) {
        this.typemateriel = typemateriel;
    }
    
    public enum typeTest{
        BHK, BHKADO, pangramme

    }
    
    public enum typeMateriel{
        graphique, tactile, tablette

    }
}

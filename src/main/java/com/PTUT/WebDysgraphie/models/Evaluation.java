package com.PTUT.WebDysgraphie.models;

public class Evaluation {
    private Patient patient;

    public Evaluation(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

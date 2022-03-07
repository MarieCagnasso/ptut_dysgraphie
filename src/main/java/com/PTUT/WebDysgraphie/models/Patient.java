package com.PTUT.WebDysgraphie.models;

import java.util.Date;


public class Patient {
    private String nom;
    private Integer anonymat;
    private String sexe;
    private Integer age;
    private Date dateExamen;
    private Integer evalution;
    private String evaluationAComparer;
    private classes classe;

    public Patient(String nom, Integer anonymat, String sexe, Integer age, Date dateExamen, Integer evalution, String evaluationAComparer, classes classe) {
        this.nom = nom;
        this.anonymat = anonymat;
        this.sexe = sexe;
        this.age = age;
        this.dateExamen = dateExamen;
        this.evalution = evalution;
        this.evaluationAComparer = evaluationAComparer;
        this.classe = classe;
    }

    public enum classes{
        CP, CE1, CE2, CM1, CM2

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAnonymat() {
        return anonymat;
    }

    public void setAnonymat(Integer anonymat) {
        this.anonymat = anonymat;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public classes getClasse() {
        return classe;
    }

    public void setClasse(classes classe) {
        this.classe = classe;
    }

    public Date getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(Date dateExamen) {
        this.dateExamen = dateExamen;
    }

    public Integer getEvalution() {
        return evalution;
    }

    public void setEvalution(Integer evalution) {
        this.evalution = evalution;
    }

    public String getEvaluationAComparer() {
        return evaluationAComparer;
    }

    public void setEvaluationAComparer(String evaluationAComparer) {
        this.evaluationAComparer = evaluationAComparer;
    }
}

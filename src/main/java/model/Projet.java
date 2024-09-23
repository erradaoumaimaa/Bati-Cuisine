package model;

import Enum.EtatProjet;

import java.util.*;

public class Projet {
    private Integer id;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private double surfaceCuisine;
    private EtatProjet etatProjet;
    private Integer clientId;


    // Constructeur par défaut :
    public Projet() {}

    // Constructeur avec tous les attributs :
    public Projet(String nomProjet, double margeBeneficiaire, double coutTotal,double surfaceCuisine, EtatProjet etatProjet, Integer clientId) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.surfaceCuisine = surfaceCuisine;
        this.etatProjet = etatProjet;
        this.clientId = clientId;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public double getSurfaceCuisine(){
        return surfaceCuisine;
    }

    public void setSurfaceCuisine(double surfaceCuisine) {
        this.surfaceCuisine = surfaceCuisine;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", etatProjet=" + etatProjet +
                ", client_ID=" + clientId +
                '}';
    }
}

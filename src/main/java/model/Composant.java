package model;

import java.util.*;

public class Composant {
    protected Integer id;
    protected String nom;
    protected String typeComposant;
    protected double tauxTVA;
    protected Integer projetId;
    // Constructeur par défaut :
    public Composant() {}

    // Constructeur avec tous les attributs :
    public Composant(Integer id, String nom, String typeComposant, double tauxTVA,Integer projetId) {
        this.id = id;
        this.nom = nom;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
        this.projetId=projetId;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }
    public Integer getProjetId(){
        return projetId;
    }
    public void setProjetId(Integer projetId) {
        this.projetId = projetId;
    }
    @Override
    public String toString() {
        return "Composant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                '}';
    }
}

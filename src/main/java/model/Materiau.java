package model;

import java.util.*;

public class Materiau extends Composant {

    private double coutUnitaire;
    private double quantite;
    private double coutTransport;
    private double coefficientQualite;

    // Constructeur par défaut :
    public Materiau() {
        super();
    }

    // Constructeur avec tous les attributs :
    public Materiau(UUID id, String nom, String typeComposant, double tauxTVA, double coutUnitaire, double quantite, double coutTransport, double coefficientQualite) {
        super(id, nom, typeComposant, tauxTVA);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    // Getters et Setters :
    public double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "Materiau{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                ", coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}

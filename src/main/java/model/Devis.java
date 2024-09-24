package model;

import java.time.LocalDate;

public class Devis {
    private Integer id;
    private double montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;
    private Integer projetId;
    private Projet project;

    // Constructeur par défaut
    public Devis() {}

    // Constructeur avec tous les attributs
    public Devis(Integer id, double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte, Integer projetId, Projet project) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetId = projetId;
        this.project = project; // Initialisation de project
    }
    public Devis(Integer id, LocalDate dateEmission, LocalDate dateValidite, boolean accepte, int projetId) {
        this.id = id;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projetId = projetId;
    }

    // Autre constructeur sans projectId et project
    public Devis(Integer id, double montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean getAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public Integer getProjectId() {
        return projetId;
    }

    public void setProjectId(Integer projectId) {
        this.projetId = projectId;
    }

    public Projet getProject() {
        return project;
    }

    public void setProject(Projet project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Devis{" +
                "id=" + id +
                ", montantEstime=" + montantEstime +
                ", dateEmission=" + dateEmission +
                ", dateValidite=" + dateValidite +
                ", accepte=" + accepte +
                ", Nom projet=" + (project != null ? project.getNomProjet() : "non défini") +
                '}';
    }
}

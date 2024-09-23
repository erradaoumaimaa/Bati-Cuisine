package model;

import java.io.Serial;

public class Client {
    private Integer id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean estProfessionnel;

    // Constructeur par défaut :
    public Client() {

    }
    public Client(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    // Constructeur avec tous les attributs :
    public Client(String nom, String adresse, String telephone, boolean estProfessionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.estProfessionnel = estProfessionnel;
    }

    // Getters et Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean getEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                " nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer generatedId) {
        this.id = generatedId;
    }
}

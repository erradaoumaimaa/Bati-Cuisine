package service.interfaces;

import model.Devis;

public interface DevisService {
    boolean ajouterDevis(Devis devis);
    Devis getDevisById(int id);
    void mettreAJourEtatProjet(int projetId);
}

package repository.interfaces;

import model.Devis;

public interface DevisRepository {
    boolean ajouterDevis(Devis devis);
    Devis findById(int id);
    void updateEtatProjet(int projetId);
}

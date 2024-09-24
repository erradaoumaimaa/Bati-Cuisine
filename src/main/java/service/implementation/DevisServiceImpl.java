package service.implementation;

import model.Devis;
import repository.implementation.DevisRepositoryImpl;
import repository.interfaces.DevisRepository;
import service.interfaces.DevisService;

import java.sql.Connection;

public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;

    public DevisServiceImpl(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }
    public DevisServiceImpl(Connection connection){
        this.devisRepository = new DevisRepositoryImpl(connection);
    }
    @Override
    public boolean ajouterDevis(Devis devis) {
        return devisRepository.ajouterDevis(devis);
    }

    @Override
    public Devis getDevisById(int id) {
        return devisRepository.findById(id);
    }

    @Override
    public void mettreAJourEtatProjet(int projetId) {
        devisRepository.updateEtatProjet(projetId);
    }
}

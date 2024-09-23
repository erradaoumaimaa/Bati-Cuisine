package service.implementation;

import model.MainOeuvre;
import repository.implementation.ClientRepositoryImpl;
import repository.implementation.MainOeuvreRepositoryImpl;
import repository.interfaces.MainOeuvreRepository;
import service.interfaces.MainOeuvreService;

import java.sql.Connection;
import java.util.List;

public class MainOeuvreServiceImpl implements MainOeuvreService {
    private MainOeuvreRepository mainOeuvreRepository;

    // Constructeur pour injecter le repository
    public MainOeuvreServiceImpl(MainOeuvreRepository mainOeuvreRepository) {
        this.mainOeuvreRepository = mainOeuvreRepository;
    }
    public MainOeuvreServiceImpl(Connection connection) {
        this.mainOeuvreRepository = new MainOeuvreRepositoryImpl(connection);
    }
    // Méthode pour ajouter une main-d'œuvre
    @Override
    public boolean ajouterMainOeuvre(MainOeuvre mainOeuvre) {
        return mainOeuvreRepository.ajouterMainOeuvre(mainOeuvre);
    }

    // Méthode pour obtenir la liste des main-d'œuvre par projet ID
    @Override
    public List<MainOeuvre> getMainOeuvresByProjetId(int projetId) {
        return mainOeuvreRepository.getMainOeuvresByProjetId(projetId);
    }
}

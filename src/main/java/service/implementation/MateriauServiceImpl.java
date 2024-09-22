package service.implementation;

import model.Materiau;
import repository.implementation.MateriauRepositoryImpl;
import repository.interfaces.MateriauRepository;
import service.interfaces.MateriauService;

import java.sql.Connection;
import java.util.List;

public class MateriauServiceImpl implements MateriauService {
    private MateriauRepository materiauRepository;

    public MateriauServiceImpl(Connection connection) {
        this.materiauRepository = new MateriauRepositoryImpl(connection);
    }

    @Override
    public boolean ajouterMateriau(Materiau materiau) {
        return materiauRepository.ajouterMateriau(materiau);
    }


    @Override
    public List<Materiau> getMateriauxByProjetId(int projetId) {
        return materiauRepository.getMateriauxByProjetId(projetId);
    }
}

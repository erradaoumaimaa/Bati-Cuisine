package service.implementation;

import model.Projet;
import repository.implementation.ProjectRepositoryImpl;
import repository.interfaces.ProjectRepository;
import service.interfaces.ProjectService;

import java.sql.Connection;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectServiceImpl(Connection connection){
        this.projectRepository = new ProjectRepositoryImpl(connection);
    }
    @Override
    public boolean ajouterProjet(Projet projet) {
        return projectRepository.ajouterProjet(projet);
    }


}

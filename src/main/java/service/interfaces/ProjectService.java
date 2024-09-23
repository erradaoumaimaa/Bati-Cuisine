package service.interfaces;

import model.Projet;

import java.util.List;

public interface ProjectService  {

    boolean ajouterProjet(Projet projet);
    List<Projet> afficherTousClientsAvecProjets();
}

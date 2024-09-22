package service.interfaces;

import model.MainOeuvre;

import java.util.List;

public interface MainOeuvreService {
    boolean ajouterMainOeuvre(MainOeuvre mainOeuvre);
    List<MainOeuvre> getMainOeuvresByProjetId(int projetId);
}

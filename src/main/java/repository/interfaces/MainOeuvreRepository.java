package repository.interfaces;

import model.MainOeuvre;

import java.util.List;

public interface MainOeuvreRepository {
    boolean ajouterMainOeuvre(MainOeuvre mainOeuvre);
    List<MainOeuvre> getMainOeuvresByProjetId(int projetId);
}

package repository.interfaces;

import model.Materiau;

import java.util.List;

public interface MateriauRepository {

    boolean ajouterMateriau(Materiau materiau);

    List<Materiau> getMateriauxByProjetId(int projetId);
}

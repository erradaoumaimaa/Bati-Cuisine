package service.interfaces;

import model.Materiau;

import java.util.List;

public interface MateriauService {
    boolean ajouterMateriau(Materiau materiau);
    List<Materiau> getMateriauxByProjetId(int projetId);
}

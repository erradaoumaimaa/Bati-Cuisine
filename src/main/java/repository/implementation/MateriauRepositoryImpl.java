package repository.implementation;

import model.Materiau;
import repository.interfaces.MateriauRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MateriauRepositoryImpl implements MateriauRepository {
    private Connection connection;


    public MateriauRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour ajouter un matériau
    @Override
    public boolean ajouterMateriau(Materiau materiau) {
        String sql = "INSERT INTO materiaux (nom, typecomposant, tauxtva, quantite, coutunitaire, projet_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, materiau.getNom());
            stmt.setString(2, materiau.getTypeComposant());
            stmt.setDouble(3, materiau.getTauxTVA());
            stmt.setDouble(4, materiau.getQuantite());
            stmt.setDouble(5, materiau.getCoutUnitaire());
            stmt.setInt(6, materiau.getProjetId()); // Assurez-vous que le projet ID est correctement set

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du matériau : " + e.getMessage());
        }
        return false;
    }


    // Méthode pour obtenir la liste des matériaux associés à un projet
    @Override
    public List<Materiau> getMateriauxByProjetId(int projetId) {
        List<Materiau> materiaux = new ArrayList<>();
        String sql = "SELECT * FROM materiau WHERE projet_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projetId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Materiau materiau = new Materiau(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("type_composant"),
                            resultSet.getDouble("taux_tva"),
                            resultSet.getInt("projet_id"),
                            resultSet.getDouble("cout_unitaire"),
                            resultSet.getDouble("quantite"),
                            resultSet.getDouble("cout_transport"),
                            resultSet.getDouble("coefficient_qualite")
                    );
                    materiaux.add(materiau);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiaux;
    }
}

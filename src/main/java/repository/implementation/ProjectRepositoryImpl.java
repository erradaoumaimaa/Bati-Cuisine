package repository.implementation;

import model.Projet;
import repository.interfaces.ProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {
    private final Connection connection;

    public ProjectRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean ajouterProjet(Projet projet) {
        String sql = "INSERT INTO projets (nomprojet, surfacecuisine, margebeneficiaire, couttotal, etatprojet, client_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projet.getNomProjet());
            stmt.setDouble(2, projet.getSurfaceCuisine());
            stmt.setDouble(3, projet.getMargeBeneficiaire());
            stmt.setDouble(4, projet.getCoutTotal());
            stmt.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);

            stmt.setInt(6, projet.getClientId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                projet.setId(rs.getInt("id"));
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du projet : " + e.getMessage());
        }
        return false;
    }







}

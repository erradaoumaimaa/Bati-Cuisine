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
        String query = "INSERT INTO projets (nomProjet, margeBeneficiaire, coutTotal, etatProjet, client_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, projet.getNomProjet());
            st.setDouble(2, projet.getMargeBeneficiaire());
            st.setDouble(3, projet.getCoutTotal());
            // Utiliser setObject() pour le type ENUM
            st.setObject(4, projet.getEtatProjet().name(), java.sql.Types.OTHER); // Utiliser java.sql.Types.OTHER pour les ENUM
            st.setInt(5, projet.getClientId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du projet : " + e.getMessage());
            return false;
        }
    }





}

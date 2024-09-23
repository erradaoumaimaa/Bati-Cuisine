package repository.implementation;

import model.Client;
import model.Projet;
import repository.interfaces.ProjectRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Enum.EtatProjet;

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
            stmt.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER); // Vérifier que la méthode name() fonctionne avec le type

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

    @Override
    public List<Projet> afficherTousProjets() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT p.id, p.nomprojet, p.surfacecuisine, p.couttotal, p.etatprojet, c.id AS client_id, c.nom AS client_nom FROM projets p JOIN clients c ON p.client_id = c.id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Récupération des colonnes sans utiliser de préfixe
                Integer id = rs.getInt("id");
                String nom = rs.getString("nomprojet");
                double surface = rs.getDouble("surfacecuisine");
                double coutTotal = rs.getDouble("couttotal");
                EtatProjet etatProjet = EtatProjet.valueOf(rs.getString("etatprojet"));

                int clientId = rs.getInt("client_id");
                String clientNom = rs.getString("client_nom");

                Client client = new Client(clientId, clientNom);
                Projet projet = new Projet(id, nom, surface, 0, coutTotal, etatProjet, client);

                projets.add(projet);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'affichage des projets : " + e.getMessage());
        }

        return projets;
    }

}

package repository.implementation;

import model.MainOeuvre;
import repository.interfaces.MainOeuvreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainOeuvreRepositoryImpl implements MainOeuvreRepository {
    private Connection connection;

    // Constructeur pour injecter la connexion
    public MainOeuvreRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour ajouter une main-d'œuvre
    @Override
    public boolean ajouterMainOeuvre(MainOeuvre mainOeuvre) {
        String sql = "INSERT INTO main_oeuvre (nom, type_composant, taux_tva, taux_horaire, heures_travail, productivite_ouvrier, projet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mainOeuvre.getNom());
            statement.setString(2, mainOeuvre.getTypeComposant());
            statement.setDouble(3, mainOeuvre.getTauxTVA());
            statement.setDouble(4, mainOeuvre.getTauxHoraire());
            statement.setDouble(5, mainOeuvre.getHeuresTravail());
            statement.setDouble(6, mainOeuvre.getProductiviteOuvrier());
            statement.setInt(7, mainOeuvre.getProjetId());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour obtenir la liste des main-d'œuvre associées à un projet
    @Override
    public List<MainOeuvre> getMainOeuvresByProjetId(int projetId) {
        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        String sql = "SELECT * FROM main_oeuvre WHERE projet_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, projetId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MainOeuvre mainOeuvre = new MainOeuvre(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("type_composant"),
                            resultSet.getDouble("taux_tva"),
                            resultSet.getInt("projet_id"),
                            resultSet.getDouble("taux_horaire"),
                            resultSet.getDouble("heures_travail"),
                            resultSet.getDouble("productivite_ouvrier")
                    );
                    mainOeuvres.add(mainOeuvre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mainOeuvres;
    }
}

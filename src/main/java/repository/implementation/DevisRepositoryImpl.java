package repository.implementation;

import model.Devis;
import repository.interfaces.DevisRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DevisRepositoryImpl implements DevisRepository {
    private final Connection connection;


    public DevisRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean ajouterDevis(Devis devis) {
        String sql = "INSERT INTO devis (dateEmission, dateValidite, accepte, projet_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(devis.getDateEmission()));
            stmt.setDate(2, java.sql.Date.valueOf(devis.getDateValidite()));
            stmt.setBoolean(3, devis.getAccepte());
            stmt.setInt(4, devis.getProjectId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Devis findById(int id) {
        String sql = "SELECT * FROM devis WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Devis(
                        rs.getInt("id"),
                        rs.getDouble("montantEstime"),
                        rs.getDate("dateEmission").toLocalDate(),
                        rs.getDate("dateValidite").toLocalDate(),
                        rs.getBoolean("accepte")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEtatProjet(int projetId) {
        String sql = "UPDATE projets SET etatprojet = 'Termine' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

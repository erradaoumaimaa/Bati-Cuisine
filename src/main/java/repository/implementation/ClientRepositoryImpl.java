package repository.implementation;

import model.Client;
import repository.interfaces.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientRepositoryImpl implements ClientRepository {
    private final HashMap<Integer, Client> clients = new HashMap<>();
    protected Connection connection;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean ajouterClient(Client client) {
        String query = "INSERT INTO Clients (nom, adresse, telephone, estProfessionnel) VALUES (?, ?, ?, ?) RETURNING id";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, client.getNom());
            st.setString(2, client.getAdresse());
            st.setString(3, client.getTelephone());
            st.setBoolean(4, client.getEstProfessionnel());

            ResultSet generatedKeys = st.executeQuery();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du client", e);
        }
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<>();
        String query = "SELECT * FROM Clients";

        try (PreparedStatement st = connection.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setEstProfessionnel(rs.getBoolean("estProfessionnel"));
                allClients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des clients", e);
        }
        return allClients;
    }

    @Override
    public Client searchClientByName(String name) {
        String query = "SELECT * FROM Clients WHERE nom = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, name);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setNom(rs.getString("nom"));
                    client.setAdresse(rs.getString("adresse"));
                    client.setTelephone(rs.getString("telephone"));
                    client.setEstProfessionnel(rs.getBoolean("estProfessionnel"));
                    return client;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du client", e);
        }
        return null;
    }




}

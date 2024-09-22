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

            // Exécution de la requête et récupération de l'ID généré
            ResultSet generatedKeys = st.executeQuery();
            if (generatedKeys.next()) {
                // Récupérer l'ID généré comme un entier
                Integer generatedId = generatedKeys.getInt(1);
                client.setId(generatedId); // Assure-toi que setId() est bien défini

                // Ajout du client au HashMap
                clients.put(client.getId(), client);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
            return false;
        }
    }



    @Override
    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<>();
        String query = "SELECT * FROM Clients";

        try (PreparedStatement st = connection.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {

                Integer id = rs.getInt("id");
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                boolean estProfessionnel = rs.getBoolean("estProfessionnel");

                // Créer un objet Client et l'ajouter à la liste
                Client client = new Client( nom, adresse, telephone, estProfessionnel);
                allClients.add(client);
                clients.put(id, client);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des clients : " + e.getMessage());
        }

        return allClients;
    }

}

package repository.implementation;

import model.Client;
import repository.interfaces.*;

import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    private HashMap<Serial,Client> clients = new HashMap<>();

protected Connection connection;
    public ClientRepositoryImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public boolean ajouterClient(Client client) {
        String query = "INSERT INTO  Clients (nom, adresse,telephone, estProfessionnel) VALUES (?,?,?,?)";
    try(PreparedStatement st = connection.prepareStatement(query)) {
        st.setString(1,client.getNom());
        st.setString(2,client.getAdresse());
        st.setString(3,client.getTelephone());
        st.setBoolean(4,client.getEstProfessionnel());
        int rowAffected = st.executeUpdate();
        return rowAffected >0;

    } catch (SQLException e) {
        System.out.println("erreur lors de l'ajout du client " +e.getMessage());
        return false;
    }
    }

    @Override
    public Client getClient(Serial id) {
            return clients.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }
    public void afficherClients() {
        List<Client> clients = getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client à afficher.");
        } else {
            for (Client client : clients) {
                System.out.println("Client: " + client.getNom() + ", Adresse: " + client.getAdresse() +
                        ", Téléphone: " + client.getTelephone() + ", Professionnel: " + client.getEstProfessionnel());
            }
        }
    }

}

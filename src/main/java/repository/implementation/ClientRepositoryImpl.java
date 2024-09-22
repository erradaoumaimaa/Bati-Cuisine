package repository.implementation;

import model.Client;
import repository.interfaces.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

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
    public Client getClient(UUID id) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return List.of();
    }
}

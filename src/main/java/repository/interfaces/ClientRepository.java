package repository.interfaces;

import model.Client;

import java.io.Serial;
import java.util.List;

public interface ClientRepository {
    boolean ajouterClient(Client client);
    Client getClient(Serial id);
    List<Client> getAllClients();
}

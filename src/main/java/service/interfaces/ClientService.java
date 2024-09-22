package service.interfaces;

import model.Client;

import java.io.Serial;
import java.util.List;

public interface ClientService {
    boolean ajouterClient(Client client);
    Client getClient(Integer id);
    List<Client> getAllClients();
}

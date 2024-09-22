package repository.interfaces;

import model.Client;
import java.util.*;

public interface ClientRepository {
    boolean ajouterClient(Client client);
    Client getClient(UUID id);
    List<Client> getAllClients();
}

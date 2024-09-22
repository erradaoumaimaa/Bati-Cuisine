package service.interfaces;

import model.Client;

import java.util.List;

public interface ClientService {
    boolean ajouterClient(Client client);
    List<Client> getAllClients();
    Client searchClientByName(String nom);
}

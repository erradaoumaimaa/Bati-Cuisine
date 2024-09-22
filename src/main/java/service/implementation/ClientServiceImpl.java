package service.implementation;

import model.Client;
import repository.implementation.ClientRepositoryImpl;
import repository.interfaces.ClientRepository;
import service.interfaces.ClientService;


import java.sql.Connection;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(Connection connection) {
        this.clientRepository = new ClientRepositoryImpl(connection);
    }

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean ajouterClient(Client client) {
        return clientRepository.ajouterClient(client);
    }



    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
    @Override
    public Client searchClientByName(String name) {
        return clientRepository.searchClientByName(name);
    }



}

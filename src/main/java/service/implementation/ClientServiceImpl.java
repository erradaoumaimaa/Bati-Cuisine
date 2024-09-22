package service.implementation;

import model.Client;
import repository.interfaces.ClientRepository;
import service.interfaces.ClientService;


import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean ajouterClient(Client client) {
        return clientRepository.ajouterClient(client);
    }

    @Override
    public Client getClient(Integer id) {
        return clientRepository.getClient(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}

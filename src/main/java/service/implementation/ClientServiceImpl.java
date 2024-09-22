package service.implementation;


import model.Client;
import repository.interfaces.ClientRepository;
import service.interfaces.ClientService;

public class ClientServiceImpl implements ClientService {
    public final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    @Override
    public boolean ajouterClient(Client client) {
        return clientRepository.ajouterClient(client);
    }
}

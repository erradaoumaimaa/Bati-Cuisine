package org.example;

import controller.ClientController;
import service.implementation.ClientServiceImpl;
import repository.implementation.ClientRepositoryImpl;
import util.JdbcConnection;

import java.sql.Connection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Récupération de la connexion via Optional
        Optional<Connection> optionalConnection = JdbcConnection.getConnection();

        // Test de la connexion si presente
        if (optionalConnection.isPresent()) {
            try (Connection connection = optionalConnection.get()) {
                // Initialisation des repositories et services
                ClientRepositoryImpl clientRepository = new ClientRepositoryImpl(connection);
                ClientServiceImpl clientService = new ClientServiceImpl(clientRepository);
                ClientController clientController = new ClientController(clientService);

                // méthode ajouter un client
             clientController.ajouterClient();
             clientController.afficherClients();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'utilisation de la connexion : " + e.getMessage());
            }
        } else {
            System.out.println("Erreur : Connexion non disponible.");
        }
    }
}

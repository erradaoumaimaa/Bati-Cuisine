package controller;

import model.Client;
import service.implementation.ClientServiceImpl;
import service.interfaces.ClientService;
import util.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AppMenu {
    private static AppMenu instance;
    private final ClientService clientService;
    private final ClientController clientController;

    private AppMenu(Connection connection) throws SQLException {
        this.clientService = new ClientServiceImpl(connection);
        this.clientController = new ClientController(clientService);
    }

    public static AppMenu getInstance(Connection connection) throws SQLException {
        if (instance == null) {
            instance = new AppMenu(connection);
        }
        return instance;
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Créer un nouveau projet");
        System.out.println("2. Afficher les projets existants");
        System.out.println("3. Calculer le coût d'un projet");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");

        int choice = tools.tryParse(scanner.nextLine());

        switch (choice) {
            case 1 -> createNewProject(scanner);
            // case 2 -> projectService.displayProjects();
            // case 3 -> projectService.calculateProjectCost();
            case 4 -> {
                System.out.println("Au revoir !");
                scanner.close();
                return;
            }
            default -> System.out.println("Choix invalide. Veuillez réessayer.");
        }

        displayMainMenu();
    }

    private void createNewProject(Scanner scanner) {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("3. Afficher la liste des clients");
        System.out.print("Choisissez une option : ");

        int choice = tools.tryParse(scanner.nextLine());
        switch (choice) {
            case 1 -> findClient(scanner);
            case 2 -> clientController.ajouterClient();
            case 3 -> clientController.afficherClients();
            default -> System.out.println("Choix invalide.");
        }
    }

    private void findClient(Scanner scanner) {
        System.out.print("Entrez le nom du client à rechercher : ");
        String clientName = scanner.nextLine();

        Client foundClient = clientService.searchClientByName(clientName);

        if (foundClient != null) {
            System.out.println("Client trouvé : " + foundClient);
            // Vous pouvez continuer le traitement ici si vous avez besoin d'autres actions sur le client
        } else {
            System.out.println("Client non trouvé.");
        }
    }

}

package controller;

import model.Client;
import service.implementation.ClientServiceImpl;
import service.implementation.ProjectServiceImpl;
import service.interfaces.ClientService;
import service.interfaces.ProjectService;
import util.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AppMenu {
    private static AppMenu instance;
    private final ClientService clientService;
    private final ClientController clientController;
    private final ProjectService projectService;
    private final ProjectController projectController;

    private AppMenu(Connection connection) throws SQLException {
        this.clientService = new ClientServiceImpl(connection);
        this.clientController = new ClientController(clientService);
        this.projectService = new ProjectServiceImpl(connection); // Initialisation du ProjectService
        this.projectController = new ProjectController(projectService, clientService, new Scanner(System.in), clientController);
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
            case 1 -> projectController.creerNouveauProjet(); // Appel à la méthode de création de projet
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
}

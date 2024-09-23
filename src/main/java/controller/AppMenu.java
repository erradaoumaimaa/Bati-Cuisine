package controller;

import service.implementation.ClientServiceImpl;
import service.implementation.MainOeuvreServiceImpl;
import service.implementation.MateriauServiceImpl;
import service.implementation.ProjectServiceImpl;
import service.interfaces.ClientService;
import service.interfaces.MainOeuvreService;
import service.interfaces.MateriauService;
import service.interfaces.ProjectService;
import util.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AppMenu {
    private static AppMenu instance; // Ajout de la variable instance pour le pattern singleton
    private final ClientService clientService;
    private final ClientController clientController;
    private final MateriauService materiauService;
    private final MainOeuvreService mainOeuvreService;
    private final ProjectService projectService;
    private final ProjectController projectController;

    // Constructeur privé pour empêcher l'instanciation extérieure
    private AppMenu(Connection connection) throws SQLException {
        this.clientService = new ClientServiceImpl(connection);
        this.clientController = new ClientController(clientService);
        this.materiauService = new MateriauServiceImpl(connection);
        this.mainOeuvreService = new MainOeuvreServiceImpl(connection);
        this.projectService = new ProjectServiceImpl(connection); // Initialisation du ProjectService
        this.projectController = new ProjectController(projectService, clientService, materiauService, mainOeuvreService, new Scanner(System.in), clientController);
    }

    // Méthode pour obtenir l'instance unique d'AppMenu
    public static AppMenu getInstance(Connection connection) throws SQLException {
        if (instance == null) {
            instance = new AppMenu(connection);
        }
        return instance;
    }

    // Méthode pour afficher le menu principal
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
            case 2 -> projectController.afficherProjetsAvecClients(); // Afficher les projets existants (méthode à implémenter)
           // case 3 -> projectController.calculerCoutProjet(); // Calculer le coût d'un projet (méthode à implémenter)
            case 4 -> {
                System.out.println("Au revoir !");
                scanner.close();
                return;
            }
            default -> System.out.println("Choix invalide. Veuillez réessayer.");
        }

        // Afficher à nouveau le menu après l'exécution d'une action
        displayMainMenu();
    }
}

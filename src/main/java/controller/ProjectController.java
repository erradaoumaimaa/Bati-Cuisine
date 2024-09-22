package controller;

import model.Client;
import model.Projet;
import service.interfaces.ClientService;
import service.interfaces.ProjectService;
import util.tools;
import Enum.EtatProjet;
import java.util.Scanner;

public class ProjectController {
    private final ProjectService projectService;
    private final ClientService clientService; // Ajout du ClientService
    private final Scanner scanner;
    private final ClientController clientController;

    public ProjectController(ProjectService projectService, ClientService clientService, Scanner scanner,ClientController clientController) {
        this.projectService = projectService;
        this.clientService = clientService; // Initialisation
        this.scanner = scanner;
        this.clientController = clientController;
    }

    public void creerNouveauProjet() {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.println("3. Afficher la liste des clients");
        System.out.print("Choisissez une option : ");

        int choice = tools.tryParse(scanner.nextLine());
        Client client = null;

        switch (choice) {
            case 1:
                client = clientController.findClient();
                break;
            case 2:
                clientController.ajouterClient();
                // Après l'ajout, tu peux rechercher le client pour continuer
                client = clientController.findClient();
                break;
            case 3:
                clientController.afficherClients();
                client = clientController.findClient();
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        if (client != null) {
            System.out.println("Client sélectionné : " + client);
            continuerCreationProjet(client.getId());
        } else {
            System.out.println("Aucun client sélectionné.");
        }
    }



    private void continuerCreationProjet(int clientId) {
        System.out.print("Entrer le nom du projet : ");
        String nomProjet = scanner.nextLine();

        System.out.print("Entrer la surface de la cuisine (en m²) : ");
        double surfaceCuisine = tools.tryParseDouble(scanner.nextLine());

        double margeBeneficiaire;
        System.out.print("Entrer la marge bénéficiaire : ");
        margeBeneficiaire = tools.tryParseDouble(scanner.nextLine());

        double coutTotal;
        System.out.print("Entrer le coût total : ");
        coutTotal = tools.tryParseDouble(scanner.nextLine());

        EtatProjet etatProjet;
        while (true) {
            System.out.print("Entrer l'état du projet (ex: En_cours, Termine) : ");
            String etatInput = scanner.nextLine();
            try {
                etatProjet = EtatProjet.valueOf(etatInput);
                break; // Sortir de la boucle si la conversion réussit
            } catch (IllegalArgumentException e) {
                System.out.println("État invalide. Utilisez des valeurs comme En_cours ou Termine.");
            }
        }

        Projet projet = new Projet(nomProjet, surfaceCuisine, margeBeneficiaire, coutTotal, etatProjet, clientId);
        boolean ajoutReussi = projectService.ajouterProjet(projet);
        System.out.println(ajoutReussi ? "Projet ajouté avec succès !" : "Erreur lors de l'ajout du projet.");
    }




}

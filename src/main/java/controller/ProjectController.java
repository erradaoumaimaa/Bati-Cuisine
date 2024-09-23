package controller;

import model.Client;
import model.MainOeuvre;
import model.Materiau;
import model.Projet;
import service.interfaces.ClientService;
import service.interfaces.MainOeuvreService;
import service.interfaces.MateriauService;
import service.interfaces.ProjectService;
import util.tools;
import Enum.EtatProjet;
import java.util.Scanner;

public class ProjectController {
    private final ProjectService projectService;
    private final ClientService clientService;
    private final MateriauService materiauService;
    private final MainOeuvreService mainOeuvreService;
    private final Scanner scanner;
    private final ClientController clientController;

    public ProjectController(ProjectService projectService, ClientService clientService,
                             MateriauService materiauService, MainOeuvreService mainOeuvreService,
                             Scanner scanner, ClientController clientController) {
        this.projectService = projectService;
        this.clientService = clientService;
        this.materiauService = materiauService;
        this.mainOeuvreService = mainOeuvreService;
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

        System.out.print("Entrer la marge bénéficiaire : ");
        double margeBeneficiaire = tools.tryParseDouble(scanner.nextLine());

        System.out.print("Entrer le coût total : ");
        double coutTotal = tools.tryParseDouble(scanner.nextLine());

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

        // Création de l'objet projet
        Projet projet = new Projet(nomProjet, surfaceCuisine, margeBeneficiaire, coutTotal, etatProjet, clientId);

        // Ajout du projet dans la base de données pour obtenir l'ID
        boolean ajoutReussi = projectService.ajouterProjet(projet);

        if (ajoutReussi) {
            // Récupération de l'ID après insertion
            int projetId = projet.getId();
            System.out.println("Projet ajouté avec succès avec l'ID : " + projetId);

            // Ajout des matériaux
            ajouterMateriaux(projetId);

            // Ajout de la main-d'œuvre
            ajouterMainOeuvre(projetId);

        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }
    }


    private void ajouterMateriaux(int projetId) {
        while (true) {
            System.out.print("Entrez le nom du matériau : ");
            String nomMateriau = scanner.nextLine();

            System.out.print("Entrez le type de composant : ");
            String typeComposant = scanner.nextLine();

            System.out.print("Entrez le taux de TVA : ");
            double tauxTVA = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            double quantite = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = tools.tryParseDouble(scanner.nextLine());

            // Créer et ajouter le matériau au projet
            Materiau materiau = new Materiau(null, nomMateriau, typeComposant, tauxTVA, projetId, coutUnitaire, quantite, coutTransport, coefficientQualite);
            boolean ajoutMateriau = materiauService.ajouterMateriau(materiau);
            System.out.println(ajoutMateriau ? "Matériau ajouté avec succès !" : "Erreur lors de l'ajout du matériau.");

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
    }


    private void ajouterMainOeuvre(int projetId) {
        while (true) {
            System.out.print("Entrez le type de main-d'œuvre : ");
            String typeMainOeuvre = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravaillees = tools.tryParseDouble(scanner.nextLine());

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productivite = tools.tryParseDouble(scanner.nextLine());

            // Créer et ajouter la main-d'œuvre au projet
            MainOeuvre mainOeuvre = new MainOeuvre(null, typeMainOeuvre, "TypeComposant", 0.0, projetId, tauxHoraire, heuresTravaillees, productivite);
            boolean ajoutMainOeuvre = mainOeuvreService.ajouterMainOeuvre(mainOeuvre);
            System.out.println(ajoutMainOeuvre ? "Main-d'œuvre ajoutée avec succès !" : "Erreur lors de l'ajout de la main-d'œuvre.");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }
    }

}

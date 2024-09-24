package controller;

import model.*;
import service.interfaces.*;
import util.tools;
import Enum.EtatProjet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProjectController {
    private final ProjectService projectService;
    private final ClientService clientService;
    private final MateriauService materiauService;
    private final MainOeuvreService mainOeuvreService;
    private final DevisService devisService;
    private final Scanner scanner;
    private final ClientController clientController;

    public ProjectController(ProjectService projectService, ClientService clientService,
                             MateriauService materiauService, MainOeuvreService mainOeuvreService,
                             DevisService devisService, Scanner scanner, ClientController clientController) {
        this.projectService = projectService;
        this.clientService = clientService;
        this.materiauService = materiauService;
        this.mainOeuvreService = mainOeuvreService;
        this.devisService = devisService;
        this.scanner = scanner;
        this.clientController = clientController;
    }

    public void creerNouveauProjet() {
        System.out.println("--- Recherche de client ---");
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
        if (surfaceCuisine <= 0) {
            System.out.println("Surface de la cuisine invalide.");
            return;
        }

        System.out.print("Entrer la marge bénéficiaire : ");
        double margeBeneficiaire = tools.tryParseDouble(scanner.nextLine());
        if (margeBeneficiaire < 0) {
            System.out.println("Marge bénéficiaire invalide.");
            return;
        }

        System.out.print("Entrer le coût total : ");
        double coutTotal = tools.tryParseDouble(scanner.nextLine());
        if (coutTotal < 0) {
            System.out.println("Coût total invalide.");
            return;
        }

        EtatProjet etatProjet;
        while (true) {
            System.out.print("Entrer l'état du projet (ex: En_cours, Termine) : ");
            String etatInput = scanner.nextLine();
            try {
                etatProjet = EtatProjet.valueOf(etatInput);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("État invalide. Utilisez des valeurs comme En_cours ou Termine.");
            }
        }

        // Création de l'objet projet
        Projet projet = new Projet(nomProjet, surfaceCuisine, margeBeneficiaire, coutTotal, etatProjet, clientId);

        // Ajout du projet dans la base de données pour obtenir l'ID
        boolean ajoutReussi = projectService.ajouterProjet(projet);

        if (ajoutReussi) {
            int projetId = projet.getId();
            System.out.println("Projet ajouté avec succès avec l'ID : " + projetId);

            // Ajout des matériaux
            ajouterMateriaux(projetId);

            // Ajout de la main-d'œuvre
            ajouterMainOeuvre(projetId);

            // Calcul du coût total (matériaux + main-d'œuvre)
            calculerCoutTotal(projet.getId());

            // Générer le devis basé sur les données du projet
            genererDevis(projet.getId());

        } else {
            System.out.println("Erreur lors de l'ajout du projet.");
        }
    }

    // Méthode pour générer un devis

    public void genererDevis(int projetId) {
        System.out.println("--- Enregistrement du Devis ---");

        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmissionInput = scanner.nextLine();
        LocalDate dateEmission = LocalDate.parse(dateEmissionInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValiditeInput = scanner.nextLine();
        LocalDate dateValidite = LocalDate.parse(dateValiditeInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez le montant estimé : ");
        double montantEstime = scanner.nextDouble();

        System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("y")) {
            Devis devis = new Devis(null, montantEstime, dateEmission, dateValidite, false, projetId);
            boolean ajoutReussi = devisService.ajouterDevis(devis);

            if (ajoutReussi) {
                System.out.println("Devis enregistré avec succès !");
            } else {
                System.out.println("Erreur lors de l'enregistrement du devis.");
            }
        } else {
            System.out.println("Enregistrement du devis annulé.");
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
            if (tauxTVA < 0 || tauxTVA > 100) {
                System.out.println("Taux de TVA invalide.");
                continue;
            }

            System.out.print("Entrez la quantité de ce matériau (en m²) : ");
            double quantite = tools.tryParseDouble(scanner.nextLine());
            if (quantite <= 0) {
                System.out.println("Quantité invalide.");
                continue;
            }

            System.out.print("Entrez le coût unitaire de ce matériau (€/m²) : ");
            double coutUnitaire = tools.tryParseDouble(scanner.nextLine());
            if (coutUnitaire <= 0) {
                System.out.println("Coût unitaire invalide.");
                continue;
            }

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double coutTransport = tools.tryParseDouble(scanner.nextLine());
            if (coutTransport < 0) {
                System.out.println("Coût de transport invalide.");
                continue;
            }

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double coefficientQualite = tools.tryParseDouble(scanner.nextLine());
            if (coefficientQualite <= 0) {
                System.out.println("Coefficient de qualité invalide.");
                continue;
            }

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
            System.out.print("Entrez le nom de la main-d'œuvre : ");
            String nomMainOeuvre = scanner.nextLine();

            System.out.print("Entrez le type de main-d'œuvre : ");
            String typeMainOeuvre = scanner.nextLine();
            System.out.print("Entrez le taux de TVA : ");
            double tauxTVA = tools.tryParseDouble(scanner.nextLine());
            if (tauxTVA < 0 || tauxTVA > 100) {
                System.out.println("Taux de TVA invalide.");
                continue;
            }

            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double tauxHoraire = tools.tryParseDouble(scanner.nextLine());
            if (tauxHoraire <= 0) {
                System.out.println("Taux horaire invalide, veuillez réessayer.");
                continue;
            }

            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravaillees = tools.tryParseDouble(scanner.nextLine());
            if (heuresTravaillees <= 0) {
                System.out.println("Heures travaillées invalides, veuillez réessayer.");
                continue;
            }

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productivite = tools.tryParseDouble(scanner.nextLine());
            if (productivite <= 0) {
                System.out.println("Facteur de productivité invalide.");
                productivite = 1.0; // Valeur par défaut
            }

            MainOeuvre mainOeuvre = new MainOeuvre(null, nomMainOeuvre, typeMainOeuvre, 0.0, projetId, tauxHoraire, heuresTravaillees, productivite);
            boolean ajoutMainOeuvre = mainOeuvreService.ajouterMainOeuvre(mainOeuvre);
            System.out.println(ajoutMainOeuvre ? "Main-d'œuvre ajoutée avec succès !" : "Erreur lors de l'ajout de la main-d'œuvre.");

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                break;
            }
        }
    }

    public void afficherProjetsAvecClients() {
        List<Projet> projets = projectService.afficherTousClientsAvecProjets();

        if (projets.isEmpty()) {
            System.out.println("Aucun projet existant.");
            return;
        }

        System.out.println("=== Liste des projets et leurs clients ===");
        for (Projet projet : projets) {
            System.out.println(projet);
        }
    }

    public double calculerCoutTotal(int projetId) {
        double totalMateriaux = 0.0;
        double totalMainOeuvre = 0.0;

        // Récupération des matériaux
        List<Materiau> materiaux = materiauService.getMateriauxByProjetId(projetId);
        System.out.println("--- Détail des Coûts ---");

        for (Materiau materiau : materiaux) {
            double coutMateriau = (materiau.getCoutUnitaire() * materiau.getQuantite()) + materiau.getCoutTransport();
            totalMateriaux += coutMateriau * materiau.getCoefficientQualite();
            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €, transport : %.2f €)\n",
                    materiau.getNom(), coutMateriau * materiau.getCoefficientQualite(),
                    materiau.getQuantite(), materiau.getCoutUnitaire(), materiau.getCoutTransport());
        }

        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", totalMateriaux);

        // Calcul de la TVA sur les matériaux
        double tvaMateriaux = totalMateriaux * 0.20;
        totalMateriaux += tvaMateriaux;
        System.out.printf("**Coût total des matériaux avec TVA (20%%) : %.2f €**\n", totalMateriaux);

        // Récupération de la main-d'œuvre
        List<MainOeuvre> mainOeuvres = mainOeuvreService.getMainOeuvresByProjetId(projetId);

        for (MainOeuvre mainOeuvre : mainOeuvres) {
            double coutMainOeuvre = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail();
            totalMainOeuvre += coutMainOeuvre * mainOeuvre.getProductiviteOuvrier();
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €, heures travaillées : %.2f h)\n",
                    mainOeuvre.getNom(), coutMainOeuvre * mainOeuvre.getProductiviteOuvrier(),
                    mainOeuvre.getTauxHoraire(), mainOeuvre.getHeuresTravail());
        }

        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", totalMainOeuvre);

        // Calcul de la TVA sur la main-d'œuvre
        double tvaMainOeuvre = totalMainOeuvre * 0.20;
        totalMainOeuvre += tvaMainOeuvre;
        System.out.printf("**Coût total de la main-d'œuvre avec TVA (20%%) : %.2f €**\n", totalMainOeuvre);

        // Calcul du coût total avant marge
        double coutTotalAvantMarge = totalMateriaux + totalMainOeuvre;
        System.out.printf("3. Coût total avant marge : %.2f €\n", coutTotalAvantMarge);

        // Calcul de la marge bénéficiaire
        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            double margeBeneficiaire = tools.tryParseDouble(scanner.nextLine()) / 100;
            double marge = coutTotalAvantMarge * margeBeneficiaire;
            coutTotalAvantMarge += marge;
            System.out.printf("**Marge bénéficiaire (%.0f%%) : %.2f €**\n", margeBeneficiaire * 100, marge);
        }

        System.out.printf("**Coût total final du projet : %.2f €**\n", coutTotalAvantMarge);

        return coutTotalAvantMarge; // Retourne le coût total final
    }

}

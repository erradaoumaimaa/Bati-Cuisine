package controller;

import model.Client;
import service.interfaces.ClientService;
import util.tools;

import java.util.List;
import java.util.Scanner;


public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
    }

    public void ajouterClient() {
        String nom;
        do {
            System.out.println("Entrer le nom du client :");
            nom = scanner.nextLine();
        } while (!tools.isValidName(nom));

        String adresse;
        do {
            System.out.println("Entrer l'adresse du client :");
            adresse = scanner.nextLine();
        } while (!tools.isValidAddress(adresse));

        String telephone;
        do {
            System.out.println("Entrer le numéro de téléphone du client :");
            telephone = scanner.nextLine();
        } while (!tools.isValidPhoneNumber(telephone));

        System.out.println("Le client est-il professionnel ? (true||false) :");
        Boolean estProfessionnel = scanner.nextBoolean();

        Client client = new Client(nom, adresse, telephone, estProfessionnel);

        System.out.println(clientService.ajouterClient(client) ? "Ajout réussi." : "Échec de l'ajout.");

    }

    public void afficherClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client à afficher.");
        } else {
            System.out.println("Vos clients :");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("|      NOM      |      ADRESSE      |   TELEPHONE   |   EST_PROFESSIONNEL  |");
            System.out.println("-------------------------------------------------------------------------");
            for (Client client : clients) {
                System.out.printf("| %-12s | %-16s | %-12s | %-16s |\n",
                        client.getNom(),
                        client.getAdresse(),
                        client.getTelephone(),
                        client.getEstProfessionnel() ? "Oui" : "Non"
                );
            }
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private void searchClientByName(Scanner scanner) {
        System.out.print("Entrez le nom du client : ");

    }




}

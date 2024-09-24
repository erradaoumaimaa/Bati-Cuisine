package org.example;

import controller.AppMenu;
import util.JdbcConnection;

import java.sql.Connection;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Connection> optionalConnection = JdbcConnection.getConnection();

        if (optionalConnection.isPresent()) {
            try (Connection connection = optionalConnection.get()) {
                // Récupération de l'instance d'AppMenu
                AppMenu appMenu = AppMenu.getInstance(connection);

                // Démarrer le menu
                appMenu.displayMainMenu();

            } catch (Exception e) {
                System.out.println("Erreur lors de l'utilisation de la connexion : " + e.getMessage());
            }
        } else {
            System.out.println("Erreur : Connexion non disponible.");
        }
    }
}

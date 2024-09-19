package util;

import java.sql.*;
import java.util.Optional;

public class JdbcConnection {

    private static Optional<Connection> cnx = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (cnx.isEmpty()) {
            String url = "jdbc:postgresql://localhost:5432/BatiCuisine";
            String user = "postgres";
            String password = "admin";

            try {
                cnx = Optional.ofNullable(DriverManager.getConnection(url, user, password));
                System.out.println("La connexion s'est bien établie.");
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la connexion à la base de données : " + ex.getMessage());
            }
        }

        return cnx;
    }
}

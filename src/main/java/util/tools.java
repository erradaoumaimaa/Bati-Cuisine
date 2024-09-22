package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class tools {
    // Vérifie si le nom est valide (non vide)
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Vérifie si l'adresse est valide (non vide)
    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    // Vérifie si le numéro de téléphone est valide (format simple)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\d{10}$"; // Format: 10 chiffres
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // Retourne 0 si pas un entier valide
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Retourne vrai si l'email est valide, faux sinon
    public static boolean isValidEmailFormat(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}


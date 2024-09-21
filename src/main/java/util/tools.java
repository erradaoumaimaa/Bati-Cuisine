package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tools {
    // return 0 =>  not a valid integer
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    // Return true => the email is valid || false if not
    public static boolean isValidEmailFormat(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }


}

package src;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validate {

    // Method to validate ZIP code
    public static boolean zip(String input) {
        if (input.matches("^[0-9]{5}$") && input instanceof String) {
            return true;
        }
        return false;
    }

    // Method to validate if age is minor
    public static boolean minor(int age) {
        if (age < 0 || age > 17) {
            return false;
        }
        return true;
    }

    // Method to validate email
    public static boolean email(String input) {
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+([.-][a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$";
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(input);
        if (m.find()) {
            System.out.println("match");
            return true;
        }
        System.out.println("does not match");
        return false;
    }

    // Method to validate latitude
    public static boolean isLat(double number) {
        return number >= -90 && number <= 90;
    }

    // Method to validate longitude
    public static boolean isLng(double number) {
        return number >= -180 && number <= 180;
    }

    // Method to validate domain name
    public static boolean isDomain(String input) {
        String pattern = "^(?!-)(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,}$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        if (m.find() && input.length() >= 1 && input.length() <= 253) {
            return true;
        }
        return false;
    }

    // Method to validate URL
    public static boolean isUrl(String input) {
        String pattern = "^(https?://)"
                + "([A-Za-z0-9-]+\\.)*"
                + "[A-Za-z0-9-]+"
                + "(\\.[A-Za-z]{2,})"
                + "(:\\d+)?"
                + "(/[^?]*)?"
                + "(\\?[A-Za-z0-9&=._-]*)?"
                + "(#\\S*)?$";
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(input);
        return m.find();
    }

    // Method to return grade based on value
    public static String grade(double value) {
        if (value < 60) {
            return "F";
        } else if (value < 70) {
            return "D";
        } else if (value < 80) {
            return "C";
        } else if (value < 90) {
            return "B";
        } else {
            return "A";
        }
    }

    // Method to sanitize SQL
    public static String sanitize(String sql) {
        String[] dangerousPatterns = {
            "(?i)(DROP|SELECT|UPDATE|DELETE|INSERT|UNION|OR|AND|COLLATE|ADMIN|--|\\*/|/\\*|;|#|=|!=|<>|\\|{2}|&{2})",
            "--.*$",
            "/\\*.*\\*/",
            "(\\s)NULL(\\s)"
        };

        for (String pattern : dangerousPatterns) {
            sql = sql.replaceAll(pattern, "");
        }

        return sql.trim();
    }

    // Method to strip "None" values
    public static String stripNull(String input) {
        return input.replace("None", "");
    }

    // Method to validate IP address (IPv4)
    public static boolean ip(String input) {
        String ipPattern = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return input.matches(ipPattern);
    }

    // Method to validate MAC address
    public static boolean mac(String input) {
        String macPattern = "^([0-9A-Fa-f]{2}[\\s:-]){5}([0-9A-Fa-f]{2})$";
        return input.matches(macPattern);
    }

    // Method to validate MD5 hash
    public static boolean md5(String input) {
        input = input.toLowerCase();
        String md5Pattern = "^[a-f0-9]{32}$";
        return input.matches(md5Pattern);
    }
}
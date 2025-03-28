package pg;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Scanner;

public class PasswordGeneratorApp {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        StringBuilder password = new StringBuilder(length);
        boolean hasUpper = false, hasSymbol = false, hasNumber = false;
        
        while (!hasUpper || !hasSymbol || !hasNumber) {
            password.setLength(0);
            hasUpper = false;
            hasSymbol = false;
            hasNumber = false;
            
            for (int i = 0; i < length; i++) {
                char ch = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
                password.append(ch);
                if (Character.isUpperCase(ch)) hasUpper = true;
                if ("!@#$%^&*()_+".indexOf(ch) >= 0) hasSymbol = true;
                if (Character.isDigit(ch)) hasNumber = true;
            }
        }
        return password.toString();
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasSymbol = false, hasNumber = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            if ("!@#$%^&*()_+".indexOf(ch) >= 0) hasSymbol = true;
            if (Character.isDigit(ch)) hasNumber = true;
        }
        return hasUpper && hasSymbol && hasNumber;
    }

    public static void savePassword(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_manager", "root", "@Santhosh420");
            String query = "INSERT INTO passwords (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Password saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void retrievePasswords(String username) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_manager", "root", "@Santhosh420");
            String query = "SELECT password FROM passwords WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Saved Password: " + rs.getString("password"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Do you want to create your own password? (yes/no)");
        String userChoice = scanner.nextLine();
        String password;
        
        if (userChoice.equalsIgnoreCase("yes")) {
            while (true) {
                System.out.println("Enter your password (min 8 chars, 1 uppercase, 1 symbol, 1 number):");
                password = scanner.nextLine();
                if (isValidPassword(password)) {
                    break;
                } else {
                    System.out.println("Invalid password. Ensure it meets the criteria.");
                }
            }
        } else {
            System.out.println("Enter password length (min 8):");
            int length = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            password = generatePassword(length);
            System.out.println("Generated Password: " + password);
        }
        
        savePassword(username, password);

        System.out.println("Retrieve passwords? (yes/no)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            retrievePasswords(username);
        }

        scanner.close();
    }
}
package vault;

import java.util.Map;
import java.util.Scanner;

/**
 * Java Notes & Password Manager (Portfolio Project)
 * Demonstrates: Java classes, HashMaps, file I/O, and menu navigation.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println(" Java Notes & Password Manager");
        System.out.println(" (Beginner–Intermediate Java Project)");
        System.out.println("====================================\n");

        try {
            VaultData data = VaultStorage.load();
            runMenu(data);
            VaultStorage.save(data);
            System.out.println("\n✅ Vault saved. Goodbye!");
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    private static void runMenu(VaultData data) {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1) Add Password");
            System.out.println("2) View Passwords");
            System.out.println("3) Add Note");
            System.out.println("4) View Notes");
            System.out.println("5) Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addPassword(data);
                case "2" -> viewPasswords(data);
                case "3" -> addNote(data);
                case "4" -> viewNotes(data);
                case "5" -> { return; }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void addPassword(VaultData data) {
        System.out.print("Service (e.g., Gmail): ");
        String service = scanner.nextLine().trim();

        System.out.print("Username/Email: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Optional note: ");
        String note = scanner.nextLine().trim();

        if (service.isEmpty()) {
            System.out.println("❌ Service cannot be empty.");
            return;
        }

        data.getCredentials().put(service.toLowerCase(), new Models.Credential(service, username, password, note));
        System.out.println("✅ Password saved.");
    }

    private static void viewPasswords(VaultData data) {
        Map<String, Models.Credential> creds = data.getCredentials();

        if (creds.isEmpty()) {
            System.out.println("(No passwords saved yet)");
            return;
        }

        System.out.println("\nSaved Passwords");
        System.out.println("-----------------------------------");
        for (Models.Credential c : creds.values()) {
            System.out.println("Service:   " + c.service());
            System.out.println("Username:  " + c.username());
            System.out.println("Password:  ********"); // hidden for safety
            if (!c.note().isEmpty()) System.out.println("Note:      " + c.note());
            System.out.println("-----------------------------------");
        }
    }

    private static void addNote(VaultData data) {
        System.out.print("Note title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Note content: ");
        String content = scanner.nextLine();

        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        data.getNotes().put(title.toLowerCase(), new Models.Note(title, content));
        System.out.println("✅ Note saved.");
    }

    private static void viewNotes(VaultData data) {
        Map<String, Models.Note> notes = data.getNotes();

        if (notes.isEmpty()) {
            System.out.println("(No notes saved yet)");
            return;
        }

        System.out.println("\nSaved Notes");
        System.out.println("-----------------------------------");
        for (Models.Note n : notes.values()) {
            System.out.println("Title:   " + n.title());
            System.out.println("Content: " + n.content());
            System.out.println("-----------------------------------");
        }
    }
}

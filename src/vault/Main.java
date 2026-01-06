package vault;

import java.util.Map;
import java.util.Scanner;

/**
 * Java Notes & Password Manager (Portfolio Project)
 *
 * Purpose:
 * - Demonstrate beginner–intermediate Java skills using a real-world style project.
 * - Store password entries and secure notes in a structured way.
 * - Save and load data using simple file handling (VaultStorage).
 *
 * Key Java concepts:
 * - Packages, classes, methods
 * - Records (Models)
 * - HashMaps (stored inside VaultData)
 * - Menu-based program flow
 * - File I/O (save/load via VaultStorage)
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Program entry point.
     * Loads existing vault data (if any), runs the menu, and saves on exit.
     */
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

    /**
     * Displays the main menu and controls user navigation.
     * Continues running until the user chooses to exit.
     */
    private static void runMenu(VaultData data) {
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1) Add Password");
            System.out.println("2) View Passwords");
            System.out.println("3) Add Note");
            System.out.println("4) View Notes");
            System.out.println("5) Search");
            System.out.println("6) Delete Entry");
            System.out.println("7) Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addPassword(data);
                case "2" -> viewPasswords(data);
                case "3" -> addNote(data);
                case "4" -> viewNotes(data);
                case "5" -> search(data);
                case "6" -> deleteEntry(data);
                case "7" -> { return; }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    /**
     * Adds a password entry including service, username, password, and an optional note.
     * Stored inside a HashMap using the service name as the key (lowercased).
     */
    private static void addPassword(VaultData data) {
        System.out.print("Service (e.g., Gmail): ");
        String service = scanner.nextLine().trim();

        if (service.isEmpty()) {
            System.out.println("❌ Service cannot be empty.");
            return;
        }

        System.out.print("Username/Email: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Optional note: ");
        String note = scanner.nextLine().trim();

        data.getCredentials().put(
                service.toLowerCase(),
                new Models.Credential(service, username, password, note)
        );

        System.out.println("✅ Password saved.");
    }

    /**
     * Displays all saved password entries.
     * Password values are hidden for safety.
     */
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

    /**
     * Adds a secure note to the vault using a title and content.
     * Stored in a HashMap using the title as the key (lowercased).
     */
    private static void addNote(VaultData data) {
        System.out.print("Note title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        System.out.print("Note content: ");
        String content = scanner.nextLine();

        data.getNotes().put(
                title.toLowerCase(),
                new Models.Note(title, content)
        );

        System.out.println("✅ Note saved.");
    }

    /**
     * Displays all saved notes.
     */
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

    /**
     * Searches both passwords and notes using a keyword.
     * - Password search checks the service name.
     * - Note search checks the title.
     */
    private static void search(VaultData data) {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("❌ Search keyword cannot be empty.");
            return;
        }

        boolean found = false;

        System.out.println("\nMatching Passwords:");
        for (Models.Credential c : data.getCredentials().values()) {
            if (c.service().toLowerCase().contains(keyword)) {
                found = true;
                System.out.println("- " + c.service() + " (" + c.username() + ")");
            }
        }
        if (!found) {
            System.out.println("(none)");
        }

        found = false;

        System.out.println("\nMatching Notes:");
        for (Models.Note n : data.getNotes().values()) {
            if (n.title().toLowerCase().contains(keyword)) {
                found = true;
                System.out.println("- " + n.title());
            }
        }
        if (!found) {
            System.out.println("(none)");
        }
    }

    /**
     * Deletes a password or note entry by key.
     * - Passwords are deleted by service name.
     * - Notes are deleted by title.
     */
    private static void deleteEntry(VaultData data) {
        System.out.print("Delete (1) Password or (2) Note? ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Enter service name to delete: ");
            String service = scanner.nextLine().trim().toLowerCase();

            if (service.isEmpty()) {
                System.out.println("❌ Service cannot be empty.");
                return;
            }

            if (data.getCredentials().remove(service) != null) {
                System.out.println("✅ Password entry deleted.");
            } else {
                System.out.println("❌ No password found for that service.");
            }

        } else if (choice.equals("2")) {
            System.out.print("Enter note title to delete: ");
            String title = scanner.nextLine().trim().toLowerCase();

            if (title.isEmpty()) {
                System.out.println("❌ Title cannot be empty.");
                return;
            }

            if (data.getNotes().remove(title) != null) {
                System.out.println("✅ Note deleted.");
            } else {
                System.out.println("❌ No note found with that title.");
            }

        } else {
            System.out.println("❌ Invalid choice.");
        }
    }
}

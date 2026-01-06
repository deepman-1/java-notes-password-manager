package vault;

import java.io.*;

/**
 * VaultStorage handles saving and loading VaultData from a file.
 * This demonstrates beginner–intermediate Java file handling.
 */
public class VaultStorage {

    private static final String FILE_NAME = "vault-data.ser";

    public static void save(VaultData data) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(data);
        }
    }

    public static VaultData load() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);

        // If no file exists yet, return a new empty vault
        if (!file.exists()) {
            return new VaultData();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (VaultData) in.readObject();
        }
    }
}

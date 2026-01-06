package vault;

import java.io.Serializable;

/**
 * Simple data models used by the vault.
 * These are "records" (Java feature) which store data cleanly.
 */
public class Models {

    // One password entry (e.g., Gmail / username / password)
    public record Credential(String service, String username, String password, String note) implements Serializable {}

    // One secure note (title + content)
    public record Note(String title, String content) implements Serializable {}
}

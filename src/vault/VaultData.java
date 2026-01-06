package vault;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * VaultData stores everything in memory:
 * - credentials (password entries)
 * - notes
 *
 * Later, we save/load this object from a file.
 */
public class VaultData implements Serializable {

    private final Map<String, Models.Credential> credentials = new HashMap<>();
    private final Map<String, Models.Note> notes = new HashMap<>();

    public Map<String, Models.Credential> getCredentials() {
        return credentials;
    }

    public Map<String, Models.Note> getNotes() {
        return notes;
    }
}

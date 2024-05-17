package example.clinicappt.models;

import java.util.UUID;

public final class User {
    private String id;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}

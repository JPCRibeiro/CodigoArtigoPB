package com.example.usercrud.model;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final String id;
    private String name;
    private String email;
    private boolean active;

    public User(String name, String email) {
        this(UUID.randomUUID().toString(), name, email, true);
    }

    public User(String id, String name, String email, boolean active) {
        this.id = Objects.requireNonNull(id, "id não pode ser nulo");
        this.name = Objects.requireNonNull(name, "name não pode ser nulo");
        this.email = Objects.requireNonNull(email, "email não pode ser nulo");
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "name não pode ser nulo");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "email não pode ser nulo");
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.example.credit_application_service.domain.model;

import java.util.Set;
import java.util.UUID;

public final class UsuarioModel {

    private final UUID id;
    private final String username;
    private final String password;
    private final Set<String> roles;

    public UsuarioModel(UUID id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Set<String> getRoles() { return roles; }
}

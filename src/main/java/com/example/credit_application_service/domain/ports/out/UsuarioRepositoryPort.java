package com.example.credit_application_service.domain.ports.out;

import com.example.credit_application_service.domain.model.UsuarioModel;

import java.util.Optional;
import java.util.UUID;
//l
public interface UsuarioRepositoryPort {
    UsuarioModel save(UsuarioModel usuario);
    Optional<UsuarioModel> findByUsername(String username);
    Optional<UsuarioModel> findById(UUID id);
}

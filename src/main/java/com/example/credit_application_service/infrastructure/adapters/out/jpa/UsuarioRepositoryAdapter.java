package com.example.credit_application_service.infrastructure.adapters.out.jpa;

import com.example.credit_application_service.domain.model.UsuarioModel;
import com.example.credit_application_service.domain.ports.out.UsuarioRepositoryPort;
import com.example.credit_application_service.infrastructure.entity.UsuarioEntity;
import com.example.credit_application_service.infrastructure.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public UsuarioModel save(UsuarioModel usuario) {
        UsuarioEntity entity = new UsuarioEntity(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRoles()
        );
        UsuarioEntity saved = jpaRepository.save(entity);
        return new UsuarioModel(saved.getId(), saved.getUsername(), saved.getPassword(), saved.getRoles());
    }

    @Override
    public Optional<UsuarioModel> findByUsername(String username) {
        return jpaRepository.findByUsername(username)
                .map(e -> new UsuarioModel(e.getId(), e.getUsername(), e.getPassword(), e.getRoles()));
    }

    @Override
    public Optional<UsuarioModel> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(e -> new UsuarioModel(e.getId(), e.getUsername(), e.getPassword(), e.getRoles()));
    }
}

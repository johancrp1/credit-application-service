package com.example.credit_application_service.infrastructure.adapters.out.jpa;

import com.example.credit_application_service.domain.model.AfiliadoModel;
import com.example.credit_application_service.domain.ports.out.AfiliadoRepositoryPort;
import com.example.credit_application_service.application.mapper.AfiliadoEntityMapper;
import com.example.credit_application_service.infrastructure.entity.AfiliadoEntity;
import com.example.credit_application_service.infrastructure.repository.AfiliadoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AfiliadoRepositoryAdapter implements AfiliadoRepositoryPort {

    private final AfiliadoJpaRepository jpaRepository;
    private final AfiliadoEntityMapper mapper;

    public AfiliadoRepositoryAdapter(AfiliadoJpaRepository jpaRepository, AfiliadoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AfiliadoModel save(AfiliadoModel afiliado) {
        AfiliadoEntity entity = mapper.toEntity(afiliado);
        AfiliadoEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AfiliadoModel> findByDocumento(String documento) {
        return jpaRepository.findByDocumento(documento).map(mapper::toDomain);
    }

    @Override
    public Optional<AfiliadoModel> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}

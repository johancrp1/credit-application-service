package com.example.credit_application_service.infrastructure.adapters.out.jpa;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.ports.out.SolicitudRepositoryPort;
import com.example.credit_application_service.application.mapper.SolicitudEntityMapper;
import com.example.credit_application_service.infrastructure.entity.SolicitudCreditoEntity;
import com.example.credit_application_service.infrastructure.entity.EstadoSolicitudEnumEntity; // Import correcto
import com.example.credit_application_service.infrastructure.repository.SolicitudJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SolicitudRepositoryAdapter implements SolicitudRepositoryPort {

    private final SolicitudJpaRepository jpaRepository;
    private final SolicitudEntityMapper mapper;

    public SolicitudRepositoryAdapter(SolicitudJpaRepository jpaRepository, SolicitudEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SolicitudCreditoModel save(SolicitudCreditoModel solicitud) {
        SolicitudCreditoEntity entity = mapper.toEntity(solicitud);
        SolicitudCreditoEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SolicitudCreditoModel> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<SolicitudCreditoModel> findByAfiliadoDocumento(String documento) {
        return jpaRepository.findByAfiliadoDocumento(documento)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudCreditoModel> findPending() {
        return jpaRepository.findByEstado(EstadoSolicitudEnumEntity.PENDIENTE) // <-- Cambio aquí
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}

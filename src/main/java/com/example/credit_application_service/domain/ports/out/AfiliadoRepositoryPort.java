package com.example.credit_application_service.domain.ports.out;

import com.example.credit_application_service.domain.model.AfiliadoModel;

import java.util.Optional;
import java.util.UUID;

public interface AfiliadoRepositoryPort {

    AfiliadoModel save(AfiliadoModel afiliado);

    Optional<AfiliadoModel> findByDocumento(String documento);

    Optional<AfiliadoModel> findById(UUID id);
}

package com.example.credit_application_service.domain.ports.out;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudRepositoryPort {

    SolicitudCreditoModel save(SolicitudCreditoModel solicitud);

    Optional<SolicitudCreditoModel> findById(UUID id);

    List<SolicitudCreditoModel> findByAfiliadoDocumento(String documento);

    List<SolicitudCreditoModel> findPending();
}

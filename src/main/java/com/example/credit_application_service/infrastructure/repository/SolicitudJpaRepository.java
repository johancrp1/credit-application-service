package com.example.credit_application_service.infrastructure.repository;

import com.example.credit_application_service.infrastructure.entity.SolicitudCreditoEntity;
import com.example.credit_application_service.infrastructure.entity.EstadoSolicitudEnumEntity; // Import externo
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SolicitudJpaRepository extends JpaRepository<SolicitudCreditoEntity, UUID> {

    List<SolicitudCreditoEntity> findByAfiliadoDocumento(String documento);

    List<SolicitudCreditoEntity> findByEstado(EstadoSolicitudEnumEntity estado);

    Optional<SolicitudCreditoEntity> findById(UUID id);
}

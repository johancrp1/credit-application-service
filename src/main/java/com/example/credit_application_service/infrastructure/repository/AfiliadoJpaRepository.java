package com.example.credit_application_service.infrastructure.repository;

import com.example.credit_application_service.infrastructure.entity.AfiliadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface AfiliadoJpaRepository extends JpaRepository<AfiliadoEntity, UUID> {
    Optional<AfiliadoEntity> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
}

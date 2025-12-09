package com.example.credit_application_service.domain.ports.out;

import com.example.credit_application_service.domain.model.EvaluacionRiesgoModel;

import java.util.Optional;
import java.util.UUID;

public interface EvaluacionRepositoryPort {

    EvaluacionRiesgoModel save(EvaluacionRiesgoModel evaluacion);

    Optional<EvaluacionRiesgoModel> findById(UUID id);
}

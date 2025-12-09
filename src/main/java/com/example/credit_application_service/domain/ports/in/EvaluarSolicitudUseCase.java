package com.example.credit_application_service.domain.ports.in;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import java.util.UUID;

public interface EvaluarSolicitudUseCase {

    // Método principal
    SolicitudCreditoModel execute(EvaluarSolicitudCommand command);

    // Comando de entrada
    record EvaluarSolicitudCommand(UUID solicitudId) {}
}
